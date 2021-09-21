package com.ivan.weather.station.persistence.service.impl;

import com.ivan.weather.station.persistence.domain.entity.Raspberry;
import com.ivan.weather.station.persistence.domain.model.RaspberryServiceModel;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;
import com.ivan.weather.station.persistence.service.api.RaspberryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaspberryServiceImpl extends BaseServiceImpl<Raspberry, RaspberryServiceModel> implements RaspberryService {

    private final RaspberryRepository raspberryRepository;

    @Autowired
    public RaspberryServiceImpl(RaspberryRepository raspberryRepository, ModelMapper modelMapper) {
        super(raspberryRepository, modelMapper);
        this.raspberryRepository = raspberryRepository;
    }

    @Override
    public void start(String id) {
        Raspberry raspberry = raspberryRepository.findById(id).orElseThrow(() -> new RuntimeException("ID not found"));
        raspberry.setStarted(true);
        raspberryRepository.update(raspberry);
    }

    @Override
    public void stop(String id) {
        Raspberry raspberry = raspberryRepository.findById(id).orElseThrow(() -> new RuntimeException("ID not found"));
        raspberry.setStarted(false);
        raspberryRepository.update(raspberry);
    }

    @Override
    protected Class<Raspberry> getEntityClass() {
        return Raspberry.class;
    }

    @Override
    protected Class<RaspberryServiceModel> getModelClass() {
        return RaspberryServiceModel.class;
    }
}
