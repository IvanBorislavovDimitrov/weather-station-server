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

    @Autowired
    public RaspberryServiceImpl(RaspberryRepository raspberryRepository, ModelMapper modelMapper) {
        super(raspberryRepository, modelMapper);
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
