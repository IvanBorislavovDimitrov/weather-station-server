package com.ivan.weather.station.persistence.service.impl;

import com.ivan.weather.station.persistence.domain.entity.Measurement;
import com.ivan.weather.station.persistence.domain.entity.Raspberry;
import com.ivan.weather.station.persistence.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.persistence.repository.api.MeasurementRepository;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;
import com.ivan.weather.station.persistence.service.api.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        String raspberryId = measurementServiceModel.getRaspberry().getId();
        Raspberry raspberry = raspberryRepository.findById(raspberryId).orElseThrow(() -> new IllegalArgumentException("Not found"));
        Measurement measurement = modelMapper.map(measurementServiceModel, Measurement.class);
        raspberry.getMeasurements().add(measurement);
        measurement.setRaspberry(raspberry);
        measurement.setAddedOn(LocalDateTime.now());
        measurementRepository.save(measurement);
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
