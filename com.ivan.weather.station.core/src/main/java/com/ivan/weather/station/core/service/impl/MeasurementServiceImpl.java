package com.ivan.weather.station.core.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.anomaly.AnomalyDetectionRuleDetector;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.service.api.MeasurementService;
import com.ivan.weather.station.persistence.entity.Measurement;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.MeasurementRepository;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;

@Service
public class MeasurementServiceImpl extends BaseServiceImpl<Measurement, MeasurementServiceModel> implements MeasurementService {

    private final RaspberryRepository raspberryRepository;
    private final MeasurementRepository measurementRepository;
    private final AnomalyDetectionRuleDetector anomalyDetectionRuleDetector;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, RaspberryRepository raspberryRepository,
                                  ModelMapper modelMapper, AnomalyDetectionRuleDetector anomalyDetectionRuleDetector) {
        super(measurementRepository, modelMapper);
        this.raspberryRepository = raspberryRepository;
        this.measurementRepository = measurementRepository;
        this.anomalyDetectionRuleDetector = anomalyDetectionRuleDetector;
    }

    @Override
    public void save(MeasurementServiceModel measurementServiceModel) {
        String raspberryRoute = measurementServiceModel.getRaspberry()
                                                       .getRoute();
        Raspberry raspberry = raspberryRepository.findByRoute(raspberryRoute)
                                                 .orElseThrow(() -> new IllegalArgumentException("Not found"));
        measurementServiceModel.getRaspberry()
                               .setName(raspberry.getName());
        anomalyDetectionRuleDetector.detectForAnomalies(measurementServiceModel, parseAnomalyDetectionRules(raspberry), raspberry.getOwner()
                                                                                                                                 .getEmail());
        Measurement measurement = modelMapper.map(measurementServiceModel, Measurement.class);
        raspberry.getMeasurements()
                 .add(measurement);
        measurement.setRaspberry(raspberry);
        measurement.setAddedOn(LocalDateTime.now(ZoneOffset.UTC));
        measurementRepository.save(measurement);
    }

    private List<AnomalyDetectionRuleServiceModel> parseAnomalyDetectionRules(Raspberry raspberry) {
        return raspberry.getAnomalyDetectionRules()
                        .stream()
                        .map(anomalyDetectionRule -> AnomalyDetectionRuleType.from(anomalyDetectionRule.getType())
                                                                             .getRuleServiceParser(anomalyDetectionRule)
                                                                             .parse())
                        .collect(Collectors.toList());
    }

    @Override
    public List<MeasurementServiceModel> getMeasurementsBetween(LocalDateTime startPeriod, LocalDateTime endPeriod, String raspberryId) {
        List<Measurement> measurements = measurementRepository.findMeasurementsBetween(startPeriod, endPeriod, raspberryId);
        return measurements.stream()
                           .map(measurement -> modelMapper.map(measurement, MeasurementServiceModel.class))
                           .collect(Collectors.toList());
    }

    @Override
    protected Class<Measurement> getEntityClass() {
        return Measurement.class;
    }

    @Override
    protected Class<MeasurementServiceModel> getModelClass() {
        return MeasurementServiceModel.class;
    }

}
