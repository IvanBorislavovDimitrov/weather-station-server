package com.ivan.weather.station.core.service.api;

import java.time.LocalDateTime;
import java.util.List;

import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;

public interface MeasurementService extends BaseService<MeasurementServiceModel> {

    List<MeasurementServiceModel> getMeasurementsBetween(LocalDateTime startPeriod, LocalDateTime endPeriod, String raspberryId);
}
