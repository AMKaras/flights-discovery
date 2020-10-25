package com.github.amkaras.flights.discovery.service;

import com.github.amkaras.flights.discovery.model.FlightDetails;

import java.time.LocalDate;
import java.util.List;

public interface FlightDetailsDiscoveryService {

    List<FlightDetails> getCurrentDetails(String origin, String destination, LocalDate from, LocalDate to);

    List<FlightDetails> getCheapestDetails(String origin, String destination, LocalDate from, LocalDate to);

    List<FlightDetails> getAverageDetails(String origin, String destination, LocalDate from, LocalDate to);
}
