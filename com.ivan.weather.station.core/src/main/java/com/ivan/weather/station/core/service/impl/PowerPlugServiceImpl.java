package com.ivan.weather.station.core.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.model.PowerPlugServiceModel;
import com.ivan.weather.station.core.service.api.PowerPlugService;
import com.ivan.weather.station.persistence.entity.PowerPlug;
import com.ivan.weather.station.persistence.repository.api.BaseRepository;

@Service
public class PowerPlugServiceImpl extends BaseServiceImpl<PowerPlug, PowerPlugServiceModel> implements PowerPlugService {

    @Autowired
    public PowerPlugServiceImpl(BaseRepository<PowerPlug> baseRepository, ModelMapper modelMapper) {
        super(baseRepository, modelMapper);
    }

    @Override
    protected Class<PowerPlug> getEntityClass() {
        return PowerPlug.class;
    }

    @Override
    protected Class<PowerPlugServiceModel> getModelClass() {
        return PowerPlugServiceModel.class;
    }
}
