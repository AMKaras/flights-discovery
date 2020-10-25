package com.github.amkaras.flights.discovery.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Repository
public class CitiesDaoImpl implements CitiesDao {

    @Override
    public Set<String> findAll() {
        return Stream.of(
                "Warsaw", "Cracow", "Rome", "Paris", "London", "Moscow", "Ankara", "Washington", "Sydney", "Tokio")
                .collect(toSet());
    }
}
