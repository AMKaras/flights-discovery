package com.github.amkaras.flights.discovery.ui.component;

import com.github.amkaras.flights.discovery.model.FlightDetails;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightsDetailsComponent extends Grid<FlightDetails> {

    private final DateTimeFormatter minutesPrecisionFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private final DateTimeFormatter secondsPrecisionFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public FlightsDetailsComponent() {
        this.addColumn(FlightDetails::getAirline)
                .setHeader("Airline").setAutoWidth(true).setSortable(true);
        this.addColumn(FlightDetails::getOrigin)
                .setHeader("Origin").setAutoWidth(true).setSortable(true);
        this.addColumn(FlightDetails::getDestination)
                .setHeader("Destination").setAutoWidth(true).setSortable(true);
        this.addColumn(flightDetails -> flightDetails.getDeparture().format(minutesPrecisionFormatter))
                .setHeader("Departure").setAutoWidth(true).setSortable(true);
        this.addColumn(flightDetails -> flightDetails.getArrival().format(minutesPrecisionFormatter))
                .setHeader("Arrival").setAutoWidth(true).setSortable(true);
        this.addColumn(FlightDetails::getPrice)
                .setHeader("Price").setAutoWidth(true).setSortable(true);
        this.addColumn(FlightDetails::getCurrency)
                .setHeader("Currency").setAutoWidth(true).setSortable(true);
        this.addColumn(flightDetails -> flightDetails.getDateChecked().format(secondsPrecisionFormatter))
                .setHeader("Date checked").setAutoWidth(true).setSortable(true);
        this.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
    }

    public void setFlightsDetails(List<FlightDetails> details) {
        this.setItems(details);
    }
}
