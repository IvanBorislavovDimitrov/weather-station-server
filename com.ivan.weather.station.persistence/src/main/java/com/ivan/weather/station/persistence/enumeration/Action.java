package com.ivan.weather.station.persistence.enumeration;

public enum Action {

    TURN_ON("on"), TURN_OFF("off");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public String getActionValue() {
        return value;
    }
}
