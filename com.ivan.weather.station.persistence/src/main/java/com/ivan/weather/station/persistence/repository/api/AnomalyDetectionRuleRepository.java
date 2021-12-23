package com.ivan.weather.station.persistence.repository.api;

import java.util.List;

import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;

public interface AnomalyDetectionRuleRepository extends BaseRepository<AnomalyDetectionRule> {

    List<AnomalyDetectionRule> findByRaspberryId(String raspberryId);

}
