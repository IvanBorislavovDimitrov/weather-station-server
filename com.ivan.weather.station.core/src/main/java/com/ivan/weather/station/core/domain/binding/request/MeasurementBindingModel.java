package com.ivan.weather.station.core.domain.binding.request;

public class MeasurementBindingModel {

    private double temperature;
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