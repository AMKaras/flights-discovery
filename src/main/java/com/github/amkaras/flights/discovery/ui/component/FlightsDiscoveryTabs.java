package com.github.amkaras.flights.discovery.ui.component;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.HashMap;
import java.util.Map;

public class FlightsDiscoveryTabs extends Tabs {

    private final Tab currentFlights = new Tab("Current flights");
    private final Tab cheapestFlights = new Tab("Cheapest flights");
    private final Tab averageFlights = new Tab("Average price flights");
    private final Map<Tab, String> routes = new HashMap<>(3);

    public FlightsDiscoveryTabs(String route) {
        this.routes.put(currentFlights, "");
        this.routes.put(cheapestFlights, "cheapest-flights");
        this.routes.put(averageFlights, "average-flights");

        this.add(currentFlights, cheapestFlights, averageFlights);
        this.addSelectedChangeListener(e -> this.getUI().ifPresent(
                ui -> ui.navigate(routes.get(e.getSelectedTab()))
        ));
        switch (route) {
            case "cheapest-flights":
                this.setSelectedTab(cheapestFlights);
                break;
            case "average-flights":
                this.setSelectedTab(averageFlights);
                break;
            case "":
                this.setSelectedTab(currentFlights);
        }
    }
}
