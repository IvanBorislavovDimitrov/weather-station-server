package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.persistence.domain.binding.RaspberryBindingModel;
import com.ivan.weather.station.persistence.domain.model.RaspberryServiceModel;
import com.ivan.weather.station.persistence.service.api.RaspberryService;
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

    @Autowired
    public RaspberryController(RaspberryService raspberryService, ModelMapper modelMapper) {
        this.raspberryService = raspberryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<RaspberryBindingModel>> getAll() {
        List<RaspberryServiceModel> raspberryServiceModels = raspberryService.findAll();
        List<RaspberryBindingModel> raspberryBindingModelStream = raspberryServiceModels.stream()
                .map(raspberryServiceModel -> modelMapper.map(raspberryServiceModel, RaspberryBindingModel.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(raspberryBindingModelStream);
    }

    @PostMapping
    public ResponseEntity<RaspberryBindingModel> create(@RequestBody RaspberryBindingModel raspberryBindingModel) {
        RaspberryServiceModel raspberryServiceModel = modelMapper.map(raspberryBindingModel, RaspberryServiceModel.class);
        raspberryService.save(raspberryServiceModel);
        return ResponseEntity.ok(raspberryBindingModel);
    }
}
