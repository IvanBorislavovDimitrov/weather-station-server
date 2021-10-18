package com.ivan.weather.station.persistence.repository.api;

import com.ivan.weather.station.persistence.domain.entity.Measurement;

import java.util.List;

public interface MeasurementRepository extends BaseRepository<Measurement> {

    List<Measurement> findMeasurementsFor24Hours(String raspberryId);
}
