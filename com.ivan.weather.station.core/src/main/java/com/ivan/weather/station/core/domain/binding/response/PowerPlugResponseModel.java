package com.ivan.weather.station.core.domain.binding.response;

public class PowerPlugResponseModel {

    private String name;
    private String route;
    private String description;
    private String raspberryId;
    private String state;
    private String actionOnBelowAnomaly;
    private String actionOnAboveAnomaly;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getRaspberryId() {
        return raspberryId;
    }

    public void setRaspberryId(String raspberryId) {
        this.raspberryId = raspberryId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getActionOnBelowAnomaly() {
        return actionOnBelowAnomaly;
    }

    public void setActionOnBelowAnomaly(String actionOnBelowAnomaly) {
        this.actionOnBelowAnomaly = actionOnBelowAnomaly;
    }

    public String getActionOnAboveAnomaly() {
        return actionOnAboveAnomaly;
    }

    public void setActionOnAboveAnomaly(String actionOnAboveAnomaly) {
        this.actionOnAboveAnomaly = actionOnAboveAnomaly;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
