package com.ivan.weather.station.core.domain.binding.type;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

import com.ivan.weather.station.core.domain.binding.parser.AnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.parser.HumidityAnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.parser.PressureAnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.parser.TemperatureAnomalyDetectionRuleParser;
import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;

public enum AnomalyDetectionRuleType {

    TEMPERATURE("temperature") {
        @Override
        public TemperatureAnomalyDetectionRuleParser getRuleParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
            return new TemperatureAnomalyDetectionRuleParser(anomalyDetectionRuleBindingModel);
        }
    },
    HUMIDITY("humidity") {
        @Override
        public HumidityAnomalyDetectionRuleParser getRuleParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
            return new HumidityAnomalyDetectionRuleParser(anomalyDetectionRuleBindingModel);
        }
    },
    PRESSURE("pressure") {
        @Override
        public PressureAnomalyDetectionRuleParser getRuleParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
            return new PressureAnomalyDetectionRuleParser(anomalyDetectionRuleBindingModel);
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

    public String getValue() {
        return value;
    }

    public abstract AnomalyDetectionRuleParser<? extends AnomalyDetectionRuleServiceModel>
           getRuleParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel);
}
