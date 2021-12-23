package com.ivan.weather.station.core.service.api;

import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;

import java.util.List;

public interface AnomalyDetectionRuleService extends BaseService<AnomalyDetectionRuleServiceModel> {

    List<AnomalyDetectionRuleServiceModel> findByRaspberryId(String raspberryId);

}
