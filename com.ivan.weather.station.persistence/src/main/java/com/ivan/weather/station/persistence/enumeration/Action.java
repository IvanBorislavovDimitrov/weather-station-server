package com.ivan.weather.station.persistence.enumeration;

import java.util.Arrays;
import java.util.Objects;

public enum Action {

    TURN_ON("on"), TURN_OFF("off");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public String getActionValue() {
        return value;
    }

    public static Action from(String value) {
        return Arrays.stream(Action.values())
                     .filter(action -> Objects.equals(action.name(), value))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Action not found:" + value));
    }
}
