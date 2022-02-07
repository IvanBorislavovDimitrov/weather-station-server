package com.ivan.weather.station.core.service.api;

import java.util.List;

import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;

public interface AnomalyDetectionRuleService extends BaseService<AnomalyDetectionRuleServiceModel> {

    List<AnomalyDetectionRuleServiceModel> findByRaspberryId(String raspberryId);

}
