package com.github.amkaras.flights.discovery.ui.view;

import com.github.amkaras.flights.discovery.ui.component.FlightsDiscoveryTabs;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AverageFlightsView extends VerticalLayout {

    public AverageFlightsView() {
        add(new FlightsDiscoveryTabs("average-flights"));
    }

    public void initListeners() {
    }
}
