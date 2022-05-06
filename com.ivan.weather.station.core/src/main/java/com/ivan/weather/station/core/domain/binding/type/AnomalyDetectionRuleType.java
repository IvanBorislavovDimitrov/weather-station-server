package com.ivan.weather.station.core.domain.binding.type;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

import com.ivan.weather.station.core.domain.binding.parser.entity.AnomalyDetectionRuleServiceParser;
import com.ivan.weather.station.core.domain.binding.parser.entity.HumidityAnomalyDetectionRuleServiceParser;
import com.ivan.weather.station.core.domain.binding.parser.entity.TemperatureAnomalyDetectionRuleServiceParser;
import com.ivan.weather.station.core.domain.binding.parser.service.AnomalyDetectionRuleBindingParser;
import com.ivan.weather.station.core.domain.binding.parser.service.HumidityAnomalyDetectionRuleBindingParser;
import com.ivan.weather.station.core.domain.binding.parser.service.TemperatureAnomalyDetectionRuleBindingParser;
import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.persistence.constant.Constants;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;

public enum AnomalyDetectionRuleType {

    TEMPERATURE(Constants.TEMPERATURE_TYPE) {
        @Override
        public TemperatureAnomalyDetectionRuleBindingParser
               getRuleBindingParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
            return new TemperatureAnomalyDetectionRuleBindingParser(anomalyDetectionRuleBindingModel);
        }

        @Override
        public AnomalyDetectionRuleServiceParser<? extends AnomalyDetectionRuleServiceModel>
               getRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule) {
            return new TemperatureAnomalyDetectionRuleServiceParser(anomalyDetectionRule);
        }
    },
    HUMIDITY(Constants.HUMIDITY_TYPE) {
        @Override
        public HumidityAnomalyDetectionRuleBindingParser
               getRuleBindingParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
            return new HumidityAnomalyDetectionRuleBindingParser(anomalyDetectionRuleBindingModel);
        }

        @Override
        public AnomalyDetectionRuleServiceParser<? extends AnomalyDetectionRuleServiceModel>
               getRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule) {
            return new HumidityAnomalyDetectionRuleServiceParser(anomalyDetectionRule);
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

    public abstract AnomalyDetectionRuleBindingParser<? extends AnomalyDetectionRuleServiceModel>
           getRuleBindingParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel);

    public abstract AnomalyDetectionRuleServiceParser<? extends AnomalyDetectionRuleServiceModel>
           getRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule);
}
