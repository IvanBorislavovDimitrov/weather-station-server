package com.ivan.weather.station.core.domain.binding.request;

import javax.validation.constraints.NotNull;

public class PowerPlugBindingModel {

    @NotNull
    private String name;
    @NotNull
    private String route;
    @NotNull
    private String description;

    private String raspberryId;
    @NotNull
    private String actionOnBelowAnomaly;
    @NotNull
    private String actionOnAboveAnomaly;
    @NotNull
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
