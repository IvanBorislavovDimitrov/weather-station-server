package com.ivan.weather.station.persistence.type;

public enum PowerPlugType {

    TEMPERATURE, HUMIDITY;

    public String getName() {
        return name().toLowerCase();
    }
}
