package com.ivan.weather.station.core.domain.model;

public class StartRaspberryRequest {

    private String hostname;

    public StartRaspberryRequest() {
    }

    public StartRaspberryRequest(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
