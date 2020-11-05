package com.github.amkaras.flights.discovery.service;

import com.github.amkaras.flights.discovery.model.DirectAirlinesFlightDetails;
import com.github.amkaras.flights.discovery.model.FlightDetails;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class FlightDetailsDiscoveryServiceImpl implements FlightDetailsDiscoveryService {

    private static final Logger log = getLogger(FlightDetailsDiscoveryServiceImpl.class);

    private final RestTemplate restTemplate = new RestTemplate();

    private final String historyMicroserviceEndpoint;
    private final Map<String, String> fallbackUrlsForCurrentFlights;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public FlightDetailsDiscoveryServiceImpl(
            @Value("${history.microservice.endpoint}") String historyMicroserviceEndpoint,
            @Value("#{${fallback.urls}}") Map<String, String> fallbackUrlsForCurrentFlights) {
        this.historyMicroserviceEndpoint = historyMicroserviceEndpoint;
        this.fallbackUrlsForCurrentFlights = fallbackUrlsForCurrentFlights;
    }

    @Override
    public List<FlightDetails> getCurrentDetails(String origin, String destination, LocalDate from, LocalDate to) {
        final String requestUrl = format("http://%s/flights/current/%s/%s/%s/%s",
                historyMicroserviceEndpoint, origin, destination, from.format(dateFormatter), to.format(dateFormatter));
        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            ResponseEntity<FlightDetails[]> flightDetailsResponseEntity = restTemplate.getForEntity(requestUrl, FlightDetails[].class);
            if (isInvalid(flightDetailsResponseEntity)) {
                log.error("Call to current flights endpoint failed. Status is {}", flightDetailsResponseEntity.getStatusCode());
                return getCurrentDetailsUsingFallbackUrls(origin, destination, from, to);
            }
            FlightDetails[] flightDetailsResponseBody = flightDetailsResponseEntity.getBody();
            log.info("Details of current flights from {} to {} fetched from {} endpoint in {}",
                    origin, destination, requestUrl, stopwatch.stop());
            return Arrays.asList(flightDetailsResponseBody);
        } catch (Exception e) {
            return getCurrentDetailsUsingFallbackUrls(origin, destination, from, to);
        }
    }

    @Override
    public List<FlightDetails> getCheapestDetails(String origin, String destination, LocalDate from, LocalDate to) {
        final String requestUrl = format("http://%s/flights/cheapest/%s/%s/%s/%s",
                historyMicroserviceEndpoint, origin, destination, from.format(dateFormatter), to.format(dateFormatter));
        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            ResponseEntity<FlightDetails[]> flightDetailsResponseEntity = restTemplate.getForEntity(requestUrl, FlightDetails[].class);
            if (isInvalid(flightDetailsResponseEntity)) {
                log.error("Call to cheapest flights endpoint failed. Status is {}", flightDetailsResponseEntity.getStatusCode());
                return Collections.emptyList();
            }
            FlightDetails[] flightDetailsResponseBody = flightDetailsResponseEntity.getBody();
            log.info("Details of cheapest flights from {} to {} fetched from {} endpoint in {}",
                    origin, destination, requestUrl, stopwatch.stop());
            return Arrays.asList(flightDetailsResponseBody);
        } catch (Exception e) {
            log.error("Exception occurred when calling cheapest flights endpoint ", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<FlightDetails> getAverageDetails(String origin, String destination, LocalDate from, LocalDate to) {
        final String requestUrl = format("http://%s/flights/average/%s/%s/%s/%s",
                historyMicroserviceEndpoint, origin, destination, from.format(dateFormatter), to.format(dateFormatter));
        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            ResponseEntity<FlightDetails[]> flightDetailsResponseEntity = restTemplate.getForEntity(requestUrl, FlightDetails[].class);
            if (isInvalid(flightDetailsResponseEntity)) {
                log.error("Call to average flights endpoint failed. Status is {}", flightDetailsResponseEntity.getStatusCode());
                return Collections.emptyList();
            }
            FlightDetails[] flightDetailsResponseBody = flightDetailsResponseEntity.getBody();
            log.info("Details of average flights from {} to {} fetched from {} endpoint in {}",
                    origin, destination, requestUrl, stopwatch.stop());
            return Arrays.asList(flightDetailsResponseBody);
        } catch (Exception e) {
            log.error("Exception occurred when calling average flights endpoint ", e);
            return Collections.emptyList();
        }
    }

    private List<FlightDetails> getCurrentDetailsUsingFallbackUrls(String origin, String destination, LocalDate from, LocalDate to) {
        List<FlightDetails> details = new ArrayList<>();
        fallbackUrlsForCurrentFlights.forEach((airline, airlineUrl) -> {
            final String requestUrl = format("http://%s/flights/%s/%s/%s/%s",
                    airlineUrl, origin, destination, from.format(dateFormatter), to.format(dateFormatter));
            try {
                Stopwatch stopwatch = Stopwatch.createStarted();
                ResponseEntity<DirectAirlinesFlightDetails[]> flightDetailsResponseEntity =
                        restTemplate.getForEntity(requestUrl, DirectAirlinesFlightDetails[].class);
                ZonedDateTime dateChecked = ZonedDateTime.now();
                if (isInvalid(flightDetailsResponseEntity)) {
                    log.error("Direct call to airline offers endpoint failed for airline {}. Status is {}",
                            airline, flightDetailsResponseEntity.getStatusCode());
                } else {
                    Stream.of(flightDetailsResponseEntity.getBody())
                            .map(directAirlinesFlightDetails ->
                                    FlightDetails.fromDirectAirlinesDetails(directAirlinesFlightDetails, airline, dateChecked))
                            .forEach(details::add);
                    log.warn("Flights details fetched directly from airline offers endpoint {} for airline {} in {}", requestUrl, airline, stopwatch.stop());
                }
            } catch (Exception e) {
                log.error("Exception occurred when calling airline offers endpoint directly ", e);
            }
        });
        return details;
    }

    private boolean isInvalid(ResponseEntity<?> responseEntity) {
        return !responseEntity.getStatusCode().is2xxSuccessful() || !responseEntity.hasBody();
    }
}
