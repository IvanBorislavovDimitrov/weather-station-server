package com.ivan.weather.station.core.domain.model;

import com.ivan.weather.station.persistence.enumeration.Action;

public class PowerPlugServiceModel extends IdServiceModel {

    private String name;
    private String route;
    private boolean isStarted;
    private String description;
    private String raspberryId;
    private String state;
    private Action actionOnBelowAnomaly;
    private Action actionOnAboveAnomaly;
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

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
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

    public Action getActionOnBelowAnomaly() {
        return actionOnBelowAnomaly;
    }

    public void setActionOnBelowAnomaly(Action actionOnBelowAnomaly) {
        this.actionOnBelowAnomaly = actionOnBelowAnomaly;
    }

    public Action getActionOnAboveAnomaly() {
        return actionOnAboveAnomaly;
    }

    public void setActionOnAboveAnomaly(Action actionOnAboveAnomaly) {
        this.actionOnAboveAnomaly = actionOnAboveAnomaly;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
