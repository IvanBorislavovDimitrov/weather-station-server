package com.ivan.weather.station.core.calculator;

import com.ivan.weather.station.core.domain.binding.response.MeasurementResponseBindingModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.service.api.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MeasurementCalculator {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementCalculator(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    public Map<LocalDateTime, MeasurementResponseBindingModel> calculateMeasurementsBetween(LocalDateTime startPeriod,
                                                                                            LocalDateTime endPeriod,
                                                                                            String raspberryId) {
        Map<LocalDateTime, MeasurementResponseBindingModel> averagedMeasurements = new HashMap<>();
        List<MeasurementServiceModel> measurements = getSortedMeasurements(startPeriod, endPeriod, raspberryId);
        LocalDateTime initialPeriod = startPeriod;
        long hoursBetween = ChronoUnit.HOURS.between(startPeriod, endPeriod);
        for (int i = 0; i < hoursBetween; i++) {
            LocalDateTime nextPeriod = initialPeriod.plusHours(1);
            List<MeasurementServiceModel> measurementsBetween = getMeasurementsBetween(initialPeriod, nextPeriod, measurements);
            averagedMeasurements.put(initialPeriod, calculateAverageMeasurement(measurementsBetween));
            initialPeriod = nextPeriod;
        }
        return averagedMeasurements;
    }

    private List<MeasurementServiceModel> getSortedMeasurements(LocalDateTime startPeriod,
                                                                LocalDateTime endPeriod,
                                                                String raspberryId) {
        return measurementService.getMeasurementsBetween(startPeriod, endPeriod, raspberryId)
                .stream()
                .sorted(Comparator.comparing(MeasurementServiceModel::getAddedOn))
                .collect(Collectors.toList());
    }

    private List<MeasurementServiceModel> getMeasurementsBetween(LocalDateTime startPeriod,
                                                                 LocalDateTime endPeriod,
                                                                 List<MeasurementServiceModel> measurements) {
        return measurements.stream()
                .filter(measurement -> measurement.getAddedOn().isAfter(startPeriod))
                .filter(measurement -> measurement.getAddedOn().isBefore(endPeriod))
                .collect(Collectors.toList());
    }

    private MeasurementResponseBindingModel calculateAverageMeasurement(List<MeasurementServiceModel> measurements) {
        double temperature = 0;
        double humidity = 0;
        double pressure = 0;
        for (MeasurementServiceModel measurement : measurements) {
            temperature += measurement.getTemperature();
            humidity += measurement.getHumidity();
            pressure += measurement.getPressure();
        }
        MeasurementResponseBindingModel measurementResponseBindingModel = new MeasurementResponseBindingModel();
        measurementResponseBindingModel.setTemperature(temperature / measurements.size());
        measurementResponseBindingModel.setHumidity(humidity / measurements.size());
        measurementResponseBindingModel.setPressure(pressure / measurements.size());
        return measurementResponseBindingModel;
    }

}
