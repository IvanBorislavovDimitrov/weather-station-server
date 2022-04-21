package com.ivan.weather.station.core.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        powerPlugServiceModel.setType(powerPlugServiceModel.getType()
                                                           .toUpperCase(Locale.ROOT));
        PowerPlug powerPlug = modelMapper.map(powerPlugServiceModel, PowerPlug.class);
        powerPlug.setRaspberry(raspberry);
        powerPlug.setState(State.TURNED_OFF);
        powerPlugRepository.save(powerPlug);
    }

    @Override
    public void update(PowerPlugServiceModel powerPlugServiceModel) {
        PowerPlug powerPlug = powerPlugRepository.findById(powerPlugServiceModel.getId())
                                                 .orElseThrow(() -> new IllegalArgumentException("Not found"));
        PowerPlug updatePowerPlug = modelMapper.map(powerPlugServiceModel, PowerPlug.class);
        updatePowerPlug.setRaspberry(powerPlug.getRaspberry());
        updatePowerPlug.setStarted(powerPlug.isStarted());
        updatePowerPlug.setState(powerPlug.getState());
        powerPlugRepository.update(updatePowerPlug);
    }

    @Override
    public List<PowerPlugServiceModel> findAllByRaspberryId(String raspberryId) {
        List<PowerPlug> powerPlugs = powerPlugRepository.findAllByRaspberryId(raspberryId);
        return powerPlugs.stream()
                         .map(powerPlug -> modelMapper.map(powerPlug, PowerPlugServiceModel.class))
                         .collect(Collectors.toList());
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
