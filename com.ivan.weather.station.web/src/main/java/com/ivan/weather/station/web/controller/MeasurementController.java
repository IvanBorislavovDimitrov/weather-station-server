package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.core.calculator.MeasurementCalculator;
import com.ivan.weather.station.core.domain.binding.request.MeasurementRequestBindingModel;
import com.ivan.weather.station.core.domain.binding.response.MeasurementResponseBindingModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.service.api.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(value = "/measurement", produces = MediaType.APPLICATION_JSON_VALUE)
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<MeasurementRequestBindingModel> create(@RequestBody MeasurementRequestBindingModel measurementRequestBindingModel) {
        MeasurementServiceModel measurementServiceModel = MeasurementServiceModel.from(measurementRequestBindingModel);
        measurementService.save(measurementServiceModel);
        return ResponseEntity.ok(measurementRequestBindingModel);
    }

    @GetMapping(value = "/twenty-four/raspberry/{raspberryId}")
    public ResponseEntity<Map<LocalDateTime, MeasurementResponseBindingModel>> getMeasurementFor24Hours(@PathVariable String raspberryId) {
        return ResponseEntity.ok(new MeasurementCalculator(measurementService)
                .calculateMeasurementsFor24Hours(raspberryId));
    }

}
