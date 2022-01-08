package com.ivan.weather.station.core.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.model.PowerPlugServiceModel;
import com.ivan.weather.station.core.service.api.PowerPlugService;
import com.ivan.weather.station.persistence.entity.PowerPlug;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.entity.State;
import com.ivan.weather.station.persistence.repository.api.PowerPlugRepository;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;

@Service
public class PowerPlugServiceImpl extends BaseServiceImpl<PowerPlug, PowerPlugServiceModel> implements PowerPlugService {

    private final PowerPlugRepository powerPlugRepository;
    private final RaspberryRepository raspberryRepository;

    @Autowired
    public PowerPlugServiceImpl(PowerPlugRepository powerPlugRepository, ModelMapper modelMapper, RaspberryRepository raspberryRepository) {
        super(powerPlugRepository, modelMapper);
        this.powerPlugRepository = powerPlugRepository;
        this.raspberryRepository = raspberryRepository;
    }

    @Override
    public void save(PowerPlugServiceModel powerPlugServiceModel) {
        Raspberry raspberry = raspberryRepository.findById(powerPlugServiceModel.getRaspberryId())
                                                 .orElseThrow(() -> new IllegalArgumentException("Not found"));
        PowerPlug powerPlug = modelMapper.map(powerPlugServiceModel, PowerPlug.class);
        powerPlug.setRaspberry(raspberry);
        powerPlug.setState(State.TURNED_OFF);
        powerPlugRepository.save(powerPlug);
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
