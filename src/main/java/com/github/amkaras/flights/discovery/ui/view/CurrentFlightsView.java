package com.github.amkaras.flights.discovery.ui.view;

import com.github.amkaras.flights.discovery.ui.component.FlightsDiscoveryTabs;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CurrentFlightsView extends VerticalLayout {

    public CurrentFlightsView() {
        add(new FlightsDiscoveryTabs(""));
    }

    public void initListeners() {
    }
}
