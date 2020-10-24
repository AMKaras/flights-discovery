package com.github.amkaras.flights.discovery;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.github.amkaras.flights.discovery")
@EnableVaadin(value = "com.github.amkaras.flights.discovery.ui")
public class FlightsDiscoveryApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FlightsDiscoveryApplication.class, args);
    }

}
