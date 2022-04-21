package com.ivan.weather.station.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ivan.weather.station.persistence.enumeration.Action;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ivan.weather.station.core.domain.binding.request.PowerPlugBindingModel;
import com.ivan.weather.station.core.domain.binding.response.PowerPlugResponseModel;
import com.ivan.weather.station.core.domain.model.PowerPlugServiceModel;
import com.ivan.weather.station.core.service.api.PowerPlugService;

@RestController
@RequestMapping(value = "/power-plug", produces = MediaType.APPLICATION_JSON_VALUE)
public class PowerPlugController {

    private final PowerPlugService powerPlugService;
    private final ModelMapper modelMapper;

    @Autowired
    public PowerPlugController(PowerPlugService powerPlugService, ModelMapper modelMapper) {
        this.powerPlugService = powerPlugService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<PowerPlugResponseModel> add(@RequestBody @Valid PowerPlugBindingModel powerPlugBindingModel) {
        PowerPlugServiceModel powerPlugServiceModel = modelMapper.map(powerPlugBindingModel, PowerPlugServiceModel.class);
        powerPlugService.save(powerPlugServiceModel);
        PowerPlugResponseModel powerPlugResponseModel = modelMapper.map(powerPlugBindingModel, PowerPlugResponseModel.class);
        return ResponseEntity.ok(powerPlugResponseModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerPlugResponseModel> get(@PathVariable String id) {
        PowerPlugServiceModel powerPlugServiceModel = powerPlugService.findById(id);
        PowerPlugResponseModel powerPlugResponseModel = modelMapper.map(powerPlugServiceModel, PowerPlugResponseModel.class);
        return ResponseEntity.ok(powerPlugResponseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PowerPlugResponseModel> update(@PathVariable("id") String powerPlugId,
                                                         @RequestBody @Valid PowerPlugBindingModel powerPlugBindingModel) {
        PowerPlugServiceModel powerPlugServiceModel = modelMapper.map(powerPlugBindingModel, PowerPlugServiceModel.class);
        powerPlugServiceModel.setId(powerPlugId);
        powerPlugServiceModel.setActionOnAboveAnomaly(Action.from(powerPlugBindingModel.getActionOnAboveAnomaly()));
        powerPlugServiceModel.setActionOnBelowAnomaly(Action.from(powerPlugBindingModel.getActionOnBelowAnomaly()));
        powerPlugService.update(powerPlugServiceModel);
        PowerPlugResponseModel powerPlugResponseModel = modelMapper.map(powerPlugBindingModel, PowerPlugResponseModel.class);
        return ResponseEntity.ok(powerPlugResponseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        powerPlugService.delete(id);
        return ResponseEntity.ok()
                             .build();
    }

    @GetMapping
    public ResponseEntity<List<PowerPlugResponseModel>> getByRaspberryId(@RequestParam String raspberryId) {
        List<PowerPlugServiceModel> powerPlugServiceModels = powerPlugService.findAllByRaspberryId(raspberryId);
        return ResponseEntity.ok(powerPlugServiceModels.stream()
                                                       .map(powerPlugServiceModel -> modelMapper.map(powerPlugServiceModel,
                                                                                                     PowerPlugResponseModel.class))
                                                       .collect(Collectors.toList()));
    }

}
