package com.github.amkaras.flights.discovery.ui.view;

import com.github.amkaras.flights.discovery.ui.component.FlightsDiscoveryTabs;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CheapestFlightsView extends VerticalLayout {

    public CheapestFlightsView() {
        add(new FlightsDiscoveryTabs("cheapest-flights"));
    }

    public void initListeners() {
    }
}
