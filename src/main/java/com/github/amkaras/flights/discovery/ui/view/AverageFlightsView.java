package com.github.amkaras.flights.discovery.ui.view;

import com.github.amkaras.flights.discovery.model.FlightDetails;
import com.github.amkaras.flights.discovery.ui.component.FlightSearchComponent;
import com.github.amkaras.flights.discovery.ui.component.FlightsDetailsComponent;
import com.github.amkaras.flights.discovery.ui.component.FlightsDiscoveryTabs;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AverageFlightsView extends VerticalLayout {

    private final FlightSearchComponent flightSearchComponent = new FlightSearchComponent();
    private final FlightsDetailsComponent flightsDetailsComponent = new FlightsDetailsComponent();

    public AverageFlightsView() {
        add(new FlightsDiscoveryTabs("average-flights"), flightSearchComponent, flightsDetailsComponent);
    }


    public void initListeners(ComponentEventListener<ClickEvent<Button>> searchButtonClickedListener) {
        flightSearchComponent.initListeners(searchButtonClickedListener);
    }

    public String getOrigin() {
        return flightSearchComponent.getOrigin();
    }

    public String getDestination() {
        return flightSearchComponent.getDestination();
    }

    public LocalDate getFrom() {
        return flightSearchComponent.getFrom();
    }

    public LocalDate getTo() {
        return flightSearchComponent.getTo();
    }

    public void setOrigins(Set<String> origins) {
        flightSearchComponent.setOrigins(origins);
    }

    public void setDestinations(Set<String> destinations) {
        flightSearchComponent.setDestinations(destinations);
    }

    public void setFlightDetails(List<FlightDetails> details) {
        flightsDetailsComponent.setFlightsDetails(details);
    }
}
