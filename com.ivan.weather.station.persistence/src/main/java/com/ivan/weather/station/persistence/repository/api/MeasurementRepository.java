package com.ivan.weather.station.persistence.repository.api;

import com.ivan.weather.station.persistence.entity.Measurement;

import java.time.LocalDateTime;
import java.util.List;

public interface MeasurementRepository extends BaseRepository<Measurement> {

    List<Measurement> findMeasurementsBetween(LocalDateTime startPeriod, LocalDateTime endPeriod, String raspberryId);
}
