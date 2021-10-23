package com.ivan.weather.station.core.domain.binding.response;

import java.time.LocalDateTime;

public class DateWithMeasurementsResponseModel {

    private LocalDateTime date;
    private MeasurementResponseModel measurement;

    public DateWithMeasurementsResponseModel() {
    }

    public DateWithMeasurementsResponseModel(LocalDateTime date, MeasurementResponseModel measurement) {
        this.date = date;
        this.measurement = measurement;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public MeasurementResponseModel getMeasurement() {
        return measurement;
    }

    public void setMeasurement(MeasurementResponseModel measurement) {
        this.measurement = measurement;
    }
}
