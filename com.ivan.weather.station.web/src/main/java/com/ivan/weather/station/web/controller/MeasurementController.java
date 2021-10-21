package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.core.calculator.MeasurementCalculator;
import com.ivan.weather.station.core.domain.binding.request.AveragedMeasurementRequestModel;
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
    private final MeasurementCalculator measurementCalculator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementCalculator measurementCalculator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementCalculator = measurementCalculator;
    }

    @PostMapping
    public ResponseEntity<MeasurementRequestBindingModel> create(@RequestBody MeasurementRequestBindingModel measurementRequestBindingModel) {
        MeasurementServiceModel measurementServiceModel = MeasurementServiceModel.from(measurementRequestBindingModel);
        measurementService.save(measurementServiceModel);
        return ResponseEntity.ok(measurementRequestBindingModel);
    }

    @GetMapping(value = "/twenty-four/raspberry/")
    public ResponseEntity<Map<LocalDateTime, MeasurementResponseBindingModel>> getMeasurementFor24Hours(@RequestBody AveragedMeasurementRequestModel averagedMeasurementRequestModel) {
        Map<LocalDateTime, MeasurementResponseBindingModel> averagedMeasurements =
                measurementCalculator.calculateMeasurementsBetween(LocalDateTime.now().minusDays(10),
                LocalDateTime.now(),
                averagedMeasurementRequestModel.getRaspberryId());
        return ResponseEntity.ok(averagedMeasurements);
    }

}
