package com.ivan.weather.station.persistence.domain.binding.request;

public class MeasurementRequestBindingModel {

    private double temperature;
    private double humidity;
    private double pressure;
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

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getRaspberryRoute() {
        return raspberryRoute;
    }

    public void setRaspberryRoute(String raspberryRoute) {
        this.raspberryRoute = raspberryRoute;
    }
}