package com.ivan.weather.station.core.domain.model;

import java.time.LocalDateTime;

import com.ivan.weather.station.core.domain.binding.request.MeasurementBindingModel;

public class MeasurementServiceModel extends IdServiceModel {

    private double temperature;
    private double humidity;
    private LocalDateTime addedOn;
    private RaspberryServiceModel raspberry;

    public static MeasurementServiceModel from(MeasurementBindingModel measurementBindingModel) {
        var measurementServiceModel = new MeasurementServiceModel();
        measurementServiceModel.setTemperature(measurementBindingModel.getTemperature());
        measurementServiceModel.setHumidity(measurementBindingModel.getHumidity());
        RaspberryServiceModel raspberryServiceModel = new RaspberryServiceModel();
        raspberryServiceModel.setRoute(measurementBindingModel.getRaspberryRoute());
        measurementServiceModel.setRaspberry(raspberryServiceModel);
        return measurementServiceModel;
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

    public RaspberryServiceModel getRaspberry() {
        return raspberry;
    }

    public void setRaspberry(RaspberryServiceModel raspberry) {
        this.raspberry = raspberry;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

}
