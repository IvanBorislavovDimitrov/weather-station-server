package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.persistence.domain.binding.request.RaspberryRequestBindingModel;
import com.ivan.weather.station.persistence.domain.model.RaspberryServiceModel;
import com.ivan.weather.station.persistence.service.api.RaspberryService;
import com.ivan.weather.station.web.initializator.RaspberryInitializator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/raspberry", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
                .map(raspberryServiceModel -> modelMapper.map(raspberryServiceModel, RaspberryRequestBindingModel.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(raspberryRequestBindingModelStream);
    }

    @PostMapping
    public ResponseEntity<RaspberryRequestBindingModel> add(@RequestBody RaspberryRequestBindingModel raspberryRequestBindingModel) {
        RaspberryServiceModel raspberryServiceModel = modelMapper.map(raspberryRequestBindingModel, RaspberryServiceModel.class);
        raspberryService.save(raspberryServiceModel);
        return ResponseEntity.ok(raspberryRequestBindingModel);
    }

    @PostMapping(value = "/initialize")
    public ResponseEntity<Void> initRaspberry(@RequestBody RaspberryRequestBindingModel requestBindingModel) {
        raspberryInitializator.initializeRaspberryStartingCall(requestBindingModel.getRoute());
        return ResponseEntity.ok().build();
    }
}
