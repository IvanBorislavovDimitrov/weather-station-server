package com.ivan.weather.station.core.domain.binding.request;

import java.time.LocalDateTime;

public class AveragedMeasurementRequestModel {

    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private String raspberryId;

    public LocalDateTime getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDateTime startPeriod) {
        this.startPeriod = startPeriod;
    }

    public LocalDateTime getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDateTime endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getRaspberryId() {
        return raspberryId;
    }

    public void setRaspberryId(String raspberryId) {
        this.raspberryId = raspberryId;
    }
}
