package com.ivan.weather.station.core.domain.binding.type;

import com.ivan.weather.station.core.domain.binding.parser.AnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.parser.HumidityAnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.parser.PressureAnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.parser.TemperatureAnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingRequest;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

public enum AnomalyDetectionRuleType {

    TEMPERATURE("temperature") {
        @Override
        public TemperatureAnomalyDetectionRuleParser getRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest) {
            return new TemperatureAnomalyDetectionRuleParser(anomalyDetectionRuleBindingRequest);
        }
    }, HUMIDITY("humidity") {
        @Override
        public HumidityAnomalyDetectionRuleParser getRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest) {
            return new HumidityAnomalyDetectionRuleParser(anomalyDetectionRuleBindingRequest);
        }
    }, PRESSURE("pressure") {
        @Override
        public PressureAnomalyDetectionRuleParser getRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest) {
            return new PressureAnomalyDetectionRuleParser(anomalyDetectionRuleBindingRequest);
        }
    };

    private final String value;

    AnomalyDetectionRuleType(String value) {
        this.value = value;
    }

    public static AnomalyDetectionRuleType from(String ruleName) {
        return Arrays.stream(AnomalyDetectionRuleType.values())
                .filter(rule -> Objects.equals(rule.value, ruleName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("Invalid type provided: \"{0}\"", ruleName)));
    }

    public abstract AnomalyDetectionRuleParser<? extends AnomalyDetectionRuleServiceModel> getRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest);
}
