package com.github.amkaras.flights.discovery.ui.presenter;

import com.github.amkaras.flights.discovery.ui.view.AverageFlightsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

import javax.annotation.PostConstruct;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Route("average-flights")
@PageTitle("Average price flights")
@Theme(value = Material.class, variant = Lumo.LIGHT)
public class AverageFlightsPresenter extends CanonicalPresenter<AverageFlightsView> {


    public AverageFlightsPresenter() {
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
