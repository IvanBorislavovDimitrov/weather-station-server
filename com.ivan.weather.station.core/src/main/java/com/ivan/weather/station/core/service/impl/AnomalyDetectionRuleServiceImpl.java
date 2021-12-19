package com.ivan.weather.station.core.service.impl;

import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.service.api.AnomalyDetectionRuleService;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.repository.api.BaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnomalyDetectionRuleServiceImpl extends BaseServiceImpl<AnomalyDetectionRule, AnomalyDetectionRuleServiceModel> implements AnomalyDetectionRuleService {

    @Autowired
    public AnomalyDetectionRuleServiceImpl(BaseRepository<AnomalyDetectionRule> baseRepository, ModelMapper modelMapper) {
        super(baseRepository, modelMapper);
    }

    @Override
    protected Class<AnomalyDetectionRule> getEntityClass() {
        return AnomalyDetectionRule.class;
    }

    @Override
    protected Class<AnomalyDetectionRuleServiceModel> getModelClass() {
        return AnomalyDetectionRuleServiceModel.class;
    }
}
