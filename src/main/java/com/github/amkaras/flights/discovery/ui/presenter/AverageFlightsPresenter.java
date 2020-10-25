package com.github.amkaras.flights.discovery.ui.presenter;

import com.github.amkaras.flights.discovery.dao.CitiesDao;
import com.github.amkaras.flights.discovery.model.FlightDetails;
import com.github.amkaras.flights.discovery.service.FlightDetailsDiscoveryService;
import com.github.amkaras.flights.discovery.ui.view.AverageFlightsView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Route("average-flights")
@PageTitle("Average price flights")
@Theme(value = Material.class, variant = Lumo.LIGHT)
public class AverageFlightsPresenter extends CanonicalPresenter<AverageFlightsView> {

    private final CitiesDao citiesDao;
    private final FlightDetailsDiscoveryService flightDetailsDiscoveryService;

    public AverageFlightsPresenter(CitiesDao citiesDao, FlightDetailsDiscoveryService flightDetailsDiscoveryService) {
        this.citiesDao = citiesDao;
        this.flightDetailsDiscoveryService = flightDetailsDiscoveryService;
    }

    @Override
    protected void refresh() {
    }

    @PostConstruct
    private void initView() {
        Set<String> cities = citiesDao.findAll();
        getView().setOrigins(cities);
        getView().setDestinations(cities);
        getView().initListeners(e -> onSearch());
    }

    private void onSearch() {
        List<FlightDetails> details = flightDetailsDiscoveryService.getAverageDetails(
                getView().getOrigin(), getView().getDestination(), getView().getFrom(), getView().getTo());
        getView().setFlightDetails(details);
    }
}
