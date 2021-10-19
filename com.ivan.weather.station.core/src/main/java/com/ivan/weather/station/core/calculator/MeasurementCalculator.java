package com.ivan.weather.station.core.calculator;

import com.ivan.weather.station.core.domain.binding.response.MeasurementResponseBindingModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.service.api.MeasurementService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MeasurementCalculator {

    private final MeasurementService measurementService;

    public MeasurementCalculator(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    public Map<LocalDateTime, MeasurementResponseBindingModel> calculateMeasurementsFor24Hours(String raspberryId) {
        Map<LocalDateTime, MeasurementResponseBindingModel> averagedMeasurements = new HashMap<>();
        List<MeasurementServiceModel> measurements = getSortedMeasurementsFor24Hours(raspberryId);
        LocalDateTime initialPeriod = LocalDateTime.now().minusDays(1);
        for (int i = 0; i < 24; i++) {
            LocalDateTime nextPeriod = initialPeriod.plusHours(i);
            List<MeasurementServiceModel> measurementsBetween = getMeasurementsBetween(initialPeriod, nextPeriod, measurements);
            averagedMeasurements.put(initialPeriod, calculateAverageMeasurement(measurementsBetween));
            initialPeriod = nextPeriod;
        }
        return averagedMeasurements;
    }

    private List<MeasurementServiceModel> getSortedMeasurementsFor24Hours(String raspberryId) {
        return measurementService.getMeasurementsFor24Hours(raspberryId)
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
