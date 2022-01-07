package com.ivan.weather.station.web.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.weather.station.core.domain.binding.request.PowerPlugRequestModel;
import com.ivan.weather.station.core.domain.binding.response.PowerPlugResponseModel;
import com.ivan.weather.station.core.domain.model.PowerPlugServiceModel;
import com.ivan.weather.station.core.service.api.PowerPlugService;

@RestController
@RequestMapping(value = "/power-plug", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PowerPlugController {

    private final PowerPlugService powerPlugService;
    private final ModelMapper modelMapper;

    @Autowired
    public PowerPlugController(PowerPlugService powerPlugService, ModelMapper modelMapper) {
        this.powerPlugService = powerPlugService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<PowerPlugResponseModel> add(@RequestBody @Valid PowerPlugRequestModel powerPlugRequestModel) {
        PowerPlugServiceModel powerPlugServiceModel = modelMapper.map(powerPlugRequestModel, PowerPlugServiceModel.class);
        powerPlugService.save(powerPlugServiceModel);
        PowerPlugResponseModel powerPlugResponseModel = modelMapper.map(powerPlugRequestModel, PowerPlugResponseModel.class);
        return ResponseEntity.ok(powerPlugResponseModel);
    }
}
