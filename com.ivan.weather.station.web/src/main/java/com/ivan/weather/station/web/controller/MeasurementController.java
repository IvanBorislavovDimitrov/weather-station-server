package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.persistence.domain.binding.MeasurementBindingModel;
import com.ivan.weather.station.persistence.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.persistence.service.api.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/measurement", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public ResponseEntity<MeasurementBindingModel> create(@RequestBody MeasurementBindingModel measurementBindingModel) {
        MeasurementServiceModel measurementServiceModel = MeasurementServiceModel.from(measurementBindingModel);
        measurementService.save(measurementServiceModel);
        return ResponseEntity.ok(measurementBindingModel);
    }
}
