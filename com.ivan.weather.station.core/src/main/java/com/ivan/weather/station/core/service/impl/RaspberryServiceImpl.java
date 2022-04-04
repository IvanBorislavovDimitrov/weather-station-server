package com.ivan.weather.station.core.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.model.RaspberryServiceModel;
import com.ivan.weather.station.core.domain.model.UserServiceModel;
import com.ivan.weather.station.core.initializator.RaspberryRemoteControl;
import com.ivan.weather.station.core.service.api.RaspberryService;
import com.ivan.weather.station.core.service.api.UserService;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;

@Service
public class RaspberryServiceImpl extends BaseServiceImpl<Raspberry, RaspberryServiceModel> implements RaspberryService {

    private final RaspberryRepository raspberryRepository;
    private final UserService userService;
    private final RaspberryRemoteControl raspberryRemoteControl;

    @Autowired
    public RaspberryServiceImpl(RaspberryRepository raspberryRepository, ModelMapper modelMapper, UserService userService,
                                RaspberryRemoteControl raspberryRemoteControl) {
        super(raspberryRepository, modelMapper);
        this.raspberryRepository = raspberryRepository;
        this.userService = userService;
        this.raspberryRemoteControl = raspberryRemoteControl;
    }

    @Override
    public void save(RaspberryServiceModel raspberryServiceModel) {
        String username = raspberryServiceModel.getOwner()
                                               .getUsername();
        UserServiceModel userServiceModel = userService.findUserByUsername(username);
        raspberryServiceModel.setOwner(userServiceModel);
        super.save(raspberryServiceModel);
    }

    @Override
    public void update(RaspberryServiceModel raspberryServiceModel) {
        RaspberryServiceModel currentRaspberryServiceModel = findById(raspberryServiceModel.getId());
        raspberryServiceModel.setStarted(currentRaspberryServiceModel.isStarted());
        super.update(raspberryServiceModel);
    }

    @Override
    public void start(String id) {
        Raspberry raspberry = raspberryRepository.findById(id)
                                                 .orElseThrow(() -> new RuntimeException("ID not found"));
        raspberryRemoteControl.startRaspberry(raspberry.getRoute());
        raspberry.setStarted(true);
        raspberryRepository.update(raspberry);
    }

    @Override
    public void stop(String id) {
        Raspberry raspberry = raspberryRepository.findById(id)
                                                 .orElseThrow(() -> new RuntimeException("ID not found"));
        raspberryRemoteControl.stopRaspberry(raspberry.getRoute());
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
