package com.ivan.weather.station.core.domain.binding.parser.entity;

import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.domain.model.RaspberryServiceModel;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;

public abstract class AnomalyDetectionRuleServiceParser<T extends AnomalyDetectionRuleServiceModel> {

    private final AnomalyDetectionRule anomalyDetectionRule;

    protected AnomalyDetectionRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule) {
        this.anomalyDetectionRule = anomalyDetectionRule;
    }

    public T parse() {
        T anomalyDetectionEntity = getModel();
        anomalyDetectionEntity.setRuleAboveActivated(anomalyDetectionRule.isRuleAboveActivated());
        anomalyDetectionEntity.setRuleBelowActivated(anomalyDetectionRule.isRuleBelowActivated());
        RaspberryServiceModel raspberryServiceModel = new RaspberryServiceModel();
        raspberryServiceModel.setId(anomalyDetectionRule.getRaspberry()
                                                        .getId());
        anomalyDetectionEntity.setRaspberry(raspberryServiceModel);
        return anomalyDetectionEntity;
    }

    protected abstract T getModel();

    public AnomalyDetectionRule getAnomalyDetectionRule() {
        return anomalyDetectionRule;
    }
}
