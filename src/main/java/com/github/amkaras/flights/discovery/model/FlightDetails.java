package com.github.amkaras.flights.discovery.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDetails {

    private final String airline;
    private final String origin;
    private final String destination;
    private final ZonedDateTime departure;
    private final ZonedDateTime arrival;
    private final BigDecimal price;
    private final String currency;
    private final ZonedDateTime dateChecked;

    @JsonCreator
    public FlightDetails(
            @JsonProperty("airline") String airline,
            @JsonProperty("origin") String origin,
            @JsonProperty("destination") String destination,
            @JsonProperty("departure") ZonedDateTime departure,
            @JsonProperty("arrival") ZonedDateTime arrival,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("currency") String currency,
            @JsonProperty("dateChecked") ZonedDateTime dateChecked) {
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.currency = currency;
        this.dateChecked = dateChecked;
    }

    public String getAirline() {
        return airline;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public ZonedDateTime getArrival() {
        return arrival;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public ZonedDateTime getDateChecked() {
        return dateChecked;
    }

    public static FlightDetails fromDirectAirlinesDetails(DirectAirlinesFlightDetails directAirlinesDetails,
                                                          String airline, ZonedDateTime dateChecked) {
        return new FlightDetails(airline, directAirlinesDetails.getOrigin(), directAirlinesDetails.getDestination(),
                directAirlinesDetails.getDeparture(), directAirlinesDetails.getArrival(),
                directAirlinesDetails.getPrice(), directAirlinesDetails.getCurrency(), dateChecked);
    }
}
