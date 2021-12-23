package com.ivan.weather.station.persistence.repository.api;

import java.time.LocalDateTime;
import java.util.List;

import com.ivan.weather.station.persistence.entity.Measurement;

public interface MeasurementRepository extends BaseRepository<Measurement> {

    List<Measurement> findMeasurementsBetween(LocalDateTime startPeriod, LocalDateTime endPeriod, String raspberryId);
}
