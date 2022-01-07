package com.ivan.weather.station.persistence.type;

public enum PowerPlugType {

    TEMPERATURE, HUMIDITY, PRESSURE;

    public String getName() {
        return name().toLowerCase();
    }
}
