package com.ivan.weather.station.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ivan.weather.station.core.domain.binding.request.RaspberryRequestBindingModel;
import com.ivan.weather.station.core.domain.model.RaspberryServiceModel;
import com.ivan.weather.station.core.domain.model.UserServiceModel;
import com.ivan.weather.station.core.service.api.RaspberryService;
import com.ivan.weather.station.web.initializator.RaspberryInitializator;

@RestController
@RequestMapping(value = "/raspberry", produces = MediaType.APPLICATION_JSON_VALUE)
public class RaspberryController {

    private final RaspberryService raspberryService;
    private final ModelMapper modelMapper;
    private final RaspberryInitializator raspberryInitializator;

    @Autowired
    public RaspberryController(RaspberryService raspberryService, ModelMapper modelMapper, RaspberryInitializator raspberryInitializator) {
        this.raspberryService = raspberryService;
        this.modelMapper = modelMapper;
        this.raspberryInitializator = raspberryInitializator;
    }

    @GetMapping
    public ResponseEntity<List<RaspberryRequestBindingModel>> getAll() {
        List<RaspberryServiceModel> raspberryServiceModels = raspberryService.findAll();
        List<RaspberryRequestBindingModel> raspberryRequestBindingModelStream = raspberryServiceModels.stream()
                                                                                                      .map(raspberryServiceModel -> modelMapper.map(raspberryServiceModel,
                                                                                                                                                    RaspberryRequestBindingModel.class))
                                                                                                      .collect(Collectors.toList());
        return ResponseEntity.ok(raspberryRequestBindingModelStream);
    }

    @PostMapping
    public ResponseEntity<RaspberryRequestBindingModel> add(@RequestBody RaspberryRequestBindingModel raspberryRequestBindingModel) {
        RaspberryServiceModel raspberryServiceModel = modelMapper.map(raspberryRequestBindingModel, RaspberryServiceModel.class);
        UserServiceModel owner = new UserServiceModel();
        owner.setUsername(SecurityContextHolder.getContext()
                                               .getAuthentication()
                                               .getName());
        raspberryServiceModel.setOwner(owner);
        raspberryService.save(raspberryServiceModel);
        return ResponseEntity.ok(raspberryRequestBindingModel);
    }

    @PostMapping(value = "/start")
    public ResponseEntity<Void> startRaspberry(@RequestBody RaspberryRequestBindingModel requestBindingModel) {
        raspberryInitializator.startRaspberry(requestBindingModel.getRoute());
        raspberryService.start(requestBindingModel.getId());
        return ResponseEntity.ok()
                             .build();
    }

    @PostMapping(value = "/stop")
    public ResponseEntity<Void> stopRaspberry(@RequestBody RaspberryRequestBindingModel requestBindingModel) {
        raspberryInitializator.stopRaspberry(requestBindingModel.getRoute());
        raspberryService.stop(requestBindingModel.getId());
        return ResponseEntity.ok()
                             .build();
    }

}
