package com.ivan.weather.station.core.service.impl;

import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.service.api.MeasurementService;
import com.ivan.weather.station.persistence.entity.Measurement;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.MeasurementRepository;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementServiceImpl extends BaseServiceImpl<Measurement, MeasurementServiceModel> implements MeasurementService {

    private final RaspberryRepository raspberryRepository;
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, RaspberryRepository raspberryRepository, ModelMapper modelMapper) {
        super(measurementRepository, modelMapper);
        this.raspberryRepository = raspberryRepository;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void save(MeasurementServiceModel measurementServiceModel) {
        String raspberryRoute = measurementServiceModel.getRaspberry().getRoute();
        Raspberry raspberry = raspberryRepository.findByRoute(raspberryRoute).orElseThrow(() -> new IllegalArgumentException("Not found"));
        Measurement measurement = modelMapper.map(measurementServiceModel, Measurement.class);
        raspberry.getMeasurements().add(measurement);
        measurement.setRaspberry(raspberry);
        measurement.setAddedOn(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    @Override
    public List<MeasurementServiceModel> getMeasurementsFor24Hours(String raspberryId) {
        List<Measurement> measurementsFor24Hours = measurementRepository.findMeasurementsFor24Hours(raspberryId);
        return measurementsFor24Hours.stream()
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
