package com.ivan.weather.station.core.service.api;

import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;

import java.util.List;

public interface MeasurementService extends BaseService<MeasurementServiceModel> {

    List<MeasurementServiceModel> getMeasurementsFor24Hours(String raspberryId);
}
