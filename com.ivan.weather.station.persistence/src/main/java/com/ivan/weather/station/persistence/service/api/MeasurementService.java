package com.ivan.weather.station.persistence.service.api;

import com.ivan.weather.station.persistence.domain.model.MeasurementServiceModel;

import java.util.List;

public interface MeasurementService extends BaseService<MeasurementServiceModel> {

    List<MeasurementServiceModel> getMeasurementsFor24Hours(String raspberryId);
}
