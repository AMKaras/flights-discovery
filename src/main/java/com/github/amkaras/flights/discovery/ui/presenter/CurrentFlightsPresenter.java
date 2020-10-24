package com.github.amkaras.flights.discovery.ui.presenter;

import com.github.amkaras.flights.discovery.ui.view.CurrentFlightsView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

import javax.annotation.PostConstruct;

@Route("")
@PageTitle("Current flights")
@Theme(value = Material.class, variant = Lumo.LIGHT)
public class CurrentFlightsPresenter extends CanonicalPresenter<CurrentFlightsView> {

    public CurrentFlightsPresenter() {
    }

    @Override
    protected void refresh() {
    }

    @PostConstruct
    private void initViewListeners() {
        view.initListeners();
        refresh();
    }
}
