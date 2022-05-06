package com.ivan.weather.station.core.domain.binding.response;

import java.time.LocalDateTime;

public class MeasurementResponseModel {

    private String id;
    private double temperature;
    private double humidity;
    private String raspberryRoute;
    private LocalDateTime addedOn;

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getRaspberryRoute() {
        return raspberryRoute;
    }

    public void setRaspberryRoute(String raspberryRoute) {
        this.raspberryRoute = raspberryRoute;
    }
}