package com.ivan.weather.station.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;
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
    public List<AnomalyDetectionRuleServiceModel> findAll() {
        List<AnomalyDetectionRule> anomalyDetectionRules = anomalyDetectionRuleRepository.findAll();
        return anomalyDetectionRules.stream()
                                    .map(anomalyDetectionRule -> AnomalyDetectionRuleType.from(anomalyDetectionRule.getType())
                                                                                         .getRuleServiceParser(anomalyDetectionRule)
                                                                                         .parse())
                                    .collect(Collectors.toList());
    }

    @Override
    public AnomalyDetectionRuleServiceModel findById(String id) {
        AnomalyDetectionRule anomalyDetectionRule = anomalyDetectionRuleRepository.findById(id)
                                                                                  .orElseThrow(() -> new IllegalArgumentException("Anomaly not found!"));
        return AnomalyDetectionRuleType.from(anomalyDetectionRule.getType())
                                       .getRuleServiceParser(anomalyDetectionRule)
                                       .parse();
    }

    @Override
    public List<AnomalyDetectionRuleServiceModel> findByRaspberryId(String raspberryId) {
        List<AnomalyDetectionRule> anomalyDetectionRules = anomalyDetectionRuleRepository.findByRaspberryId(raspberryId);
        return anomalyDetectionRules.stream()
                                    .map(anomalyDetectionRule -> AnomalyDetectionRuleType.from(anomalyDetectionRule.getType())
                                                                                         .getRuleServiceParser(anomalyDetectionRule)
                                                                                         .parse())
                                    .collect(Collectors.toList());

    }

    @Override
    public void update(AnomalyDetectionRuleServiceModel anomalyDetectionRuleServiceModel) {
        AnomalyDetectionRule anomalyDetectionRule = anomalyDetectionRuleRepository.findById(anomalyDetectionRuleServiceModel.getId())
                                                                                  .orElseThrow(() -> new IllegalArgumentException("Anomaly not found!"));
        AnomalyDetectionRule updatedAnomalyDetectionRule = anomalyDetectionRuleServiceModel.toEntityModel();
        updatedAnomalyDetectionRule.setRaspberry(anomalyDetectionRule.getRaspberry());
        anomalyDetectionRuleRepository.update(updatedAnomalyDetectionRule);
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
