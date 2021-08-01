package com.ivan.weather.station.persistence.domain.model;

import com.ivan.weather.station.persistence.domain.binding.MeasurementBindingModel;

import java.time.LocalDateTime;

public class MeasurementServiceModel extends IdServiceModel {

    private double temperature;
    private double humidity;
    private double pressure;
    private LocalDateTime addedOn;
    private RaspberryServiceModel raspberry;

    public static MeasurementServiceModel from(MeasurementBindingModel measurementBindingModel) {
        var measurementServiceModel = new MeasurementServiceModel();
        measurementServiceModel.setTemperature(measurementBindingModel.getTemperature());
        measurementServiceModel.setHumidity(measurementBindingModel.getHumidity());
        measurementServiceModel.setPressure(measurementBindingModel.getPressure());
        RaspberryServiceModel raspberryServiceModel = new RaspberryServiceModel();
        raspberryServiceModel.setId(measurementBindingModel.getRaspberryId());
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

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
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
