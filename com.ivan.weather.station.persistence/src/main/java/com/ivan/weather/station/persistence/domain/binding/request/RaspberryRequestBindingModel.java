package com.ivan.weather.station.persistence.domain.binding.request;

public class RaspberryRequestBindingModel {

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
