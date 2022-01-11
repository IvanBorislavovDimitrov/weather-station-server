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
        T anomalyDetectionServiceModel = getModel();
        anomalyDetectionServiceModel.setId(anomalyDetectionRule.getId());
        anomalyDetectionServiceModel.setRuleAboveActivated(anomalyDetectionRule.isRuleAboveActivated());
        anomalyDetectionServiceModel.setRuleBelowActivated(anomalyDetectionRule.isRuleBelowActivated());
        RaspberryServiceModel raspberryServiceModel = new RaspberryServiceModel();
        raspberryServiceModel.setId(anomalyDetectionRule.getRaspberry()
                                                        .getId());
        anomalyDetectionServiceModel.setRaspberry(raspberryServiceModel);
        return anomalyDetectionServiceModel;
    }

    protected abstract T getModel();

    public AnomalyDetectionRule getAnomalyDetectionRule() {
        return anomalyDetectionRule;
    }
}
