package com.ivan.weather.station.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ivan.weather.station.core.domain.binding.request.RaspberryBindingModel;
import com.ivan.weather.station.core.domain.model.RaspberryServiceModel;
import com.ivan.weather.station.core.domain.model.UserServiceModel;
import com.ivan.weather.station.core.service.api.RaspberryService;

@RestController
@RequestMapping(value = "/raspberry", produces = MediaType.APPLICATION_JSON_VALUE)
public class RaspberryController {

    private final RaspberryService raspberryService;
    private final ModelMapper modelMapper;

    @Autowired
    public RaspberryController(RaspberryService raspberryService, ModelMapper modelMapper) {
        this.raspberryService = raspberryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<RaspberryBindingModel>> getAll() {
        List<RaspberryServiceModel> raspberryServiceModels = raspberryService.findAll();
        List<RaspberryBindingModel> raspberryBindingModelStream = raspberryServiceModels.stream()
                                                                                        .map(raspberryServiceModel -> modelMapper.map(raspberryServiceModel,
                                                                                                                                      RaspberryBindingModel.class))
                                                                                        .collect(Collectors.toList());
        return ResponseEntity.ok(raspberryBindingModelStream);
    }

    @PostMapping
    public ResponseEntity<RaspberryBindingModel> add(@RequestBody RaspberryBindingModel raspberryBindingModel) {
        RaspberryServiceModel raspberryServiceModel = modelMapper.map(raspberryBindingModel, RaspberryServiceModel.class);
        attachUserToRaspberry(raspberryServiceModel);
        raspberryService.save(raspberryServiceModel);
        return ResponseEntity.ok(raspberryBindingModel);
    }

    @PutMapping
    public ResponseEntity<RaspberryBindingModel> update(@RequestBody RaspberryBindingModel raspberryBindingModel) {
        RaspberryServiceModel raspberryServiceModel = modelMapper.map(raspberryBindingModel, RaspberryServiceModel.class);
        attachUserToRaspberry(raspberryServiceModel);
        raspberryService.update(raspberryServiceModel);
        return ResponseEntity.ok(raspberryBindingModel);
    }

    private void attachUserToRaspberry(RaspberryServiceModel raspberryServiceModel) {
        UserServiceModel owner = new UserServiceModel();
        owner.setUsername(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName());
        raspberryServiceModel.setOwner(owner);
    }

    @PostMapping(value = "/start")
    public ResponseEntity<Void> startRaspberry(@RequestBody RaspberryBindingModel requestBindingModel) {
        raspberryService.start(requestBindingModel.getId());
        return ResponseEntity.ok()
                             .build();
    }

    @PostMapping(value = "/stop")
    public ResponseEntity<Void> stopRaspberry(@RequestBody RaspberryBindingModel requestBindingModel) {
        raspberryService.stop(requestBindingModel.getId());
        return ResponseEntity.ok()
                             .build();
    }

}
