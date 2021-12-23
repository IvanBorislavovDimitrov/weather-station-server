package com.ivan.weather.station.core.service.impl;

import com.ivan.weather.station.core.domain.model.IdServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.service.api.AnomalyDetectionRuleService;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.AnomalyDetectionRuleRepository;
import com.ivan.weather.station.persistence.repository.api.BaseRepository;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;

@Service
public class AnomalyDetectionRuleServiceImpl extends BaseServiceImpl<AnomalyDetectionRule, AnomalyDetectionRuleServiceModel>
    implements AnomalyDetectionRuleService {

    private final RaspberryRepository raspberryRepository;
    private final AnomalyDetectionRuleRepository anomalyDetectionRuleRepository;

    @Autowired
    public AnomalyDetectionRuleServiceImpl(BaseRepository<AnomalyDetectionRule> baseRepository, ModelMapper modelMapper,
                                           RaspberryRepository raspberryRepository,
                                           AnomalyDetectionRuleRepository anomalyDetectionRuleRepository) {
        super(baseRepository, modelMapper);
        this.raspberryRepository = raspberryRepository;
        this.anomalyDetectionRuleRepository = anomalyDetectionRuleRepository;
    }

    @Override
    public void save(AnomalyDetectionRuleServiceModel anomalyDetectionRuleServiceModel) {
        String raspberryId = anomalyDetectionRuleServiceModel.getRaspberry()
                                                             .getId();
        Raspberry raspberry = raspberryRepository.findById(raspberryId)
                                                 .orElseThrow(() -> new IllegalArgumentException("Raspberry not found"));
        AnomalyDetectionRule anomalyDetectionRule = anomalyDetectionRuleServiceModel.toEntityModel();
        anomalyDetectionRule.setRaspberry(raspberry);
        anomalyDetectionRuleRepository.save(anomalyDetectionRule);
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
