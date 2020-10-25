package com.github.amkaras.flights.discovery.ui.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public class FlightSearchComponent extends VerticalLayout {

    private final ComboBox<String> originCombobox = new ComboBox<>("Flight origin:");
    private final ComboBox<String> destinationCombobox = new ComboBox<>("Flight destination:");
    private final DatePicker flightRangeFromDatePicker = new DatePicker("From:");
    private final DatePicker flightRangeToDatePicker = new DatePicker("To:");
    private final Button searchButton = new Button("Search");

    public FlightSearchComponent() {
        flightRangeFromDatePicker.setValue(LocalDate.now());
        flightRangeToDatePicker.setValue(LocalDate.now().plusDays(1));
        flightRangeFromDatePicker.addValueChangeListener(
                e -> flightRangeToDatePicker.setValue(flightRangeFromDatePicker.getValue().plusDays(1)));
        searchButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        add(new HorizontalLayout(originCombobox, destinationCombobox),
                new HorizontalLayout(flightRangeFromDatePicker, flightRangeToDatePicker), searchButton);
    }

    public void initListeners(ComponentEventListener<ClickEvent<Button>> searchButtonClickedListener) {
        searchButton.addClickListener(searchButtonClickedListener);
    }

    public void setOrigins(Set<String> origins) {
        sortAndAddTo(originCombobox).accept(origins);
    }

    public void setDestinations(Set<String> destinations) {
        sortAndAddTo(destinationCombobox).accept(destinations);
    }

    public String getOrigin() {
        return originCombobox.getValue();
    }

    public String getDestination() {
        return destinationCombobox.getValue();
    }

    public LocalDate getFrom() {
        return flightRangeFromDatePicker.getValue();
    }

    public LocalDate getTo() {
        return flightRangeToDatePicker.getValue();
    }

    private Consumer<Set<String>> sortAndAddTo(ComboBox<String> comboBox) {
        return cities -> {
            List<String> sorted = cities.stream()
                    .sorted()
                    .collect(toList());
            comboBox.setItems(sorted);
        };
    }
}
