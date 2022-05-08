package com.ivan.weather.station.core.domain.binding.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MeasurementBindingModel {

    @Min(-5000)
    @Max(5000)
    private double temperature;
    @Min(-5000)
    @Max(5000)
    private double humidity;
    private String raspberryRoute;

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