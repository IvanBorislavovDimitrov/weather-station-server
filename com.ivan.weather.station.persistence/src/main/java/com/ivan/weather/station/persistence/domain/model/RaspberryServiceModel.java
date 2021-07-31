package com.ivan.weather.station.persistence.domain.model;

public class RaspberryServiceModel extends IdServiceModel {

    private String route;
    private String description;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
