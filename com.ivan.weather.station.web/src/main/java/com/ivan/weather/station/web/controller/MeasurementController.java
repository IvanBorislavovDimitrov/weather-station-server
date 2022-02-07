package com.ivan.weather.station.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ivan.weather.station.core.calculator.MeasurementCalculator;
import com.ivan.weather.station.core.domain.binding.request.AveragedMeasurementBidingModel;
import com.ivan.weather.station.core.domain.binding.request.MeasurementBindingModel;
import com.ivan.weather.station.core.domain.binding.response.DateWithMeasurementsResponseModel;
import com.ivan.weather.station.core.domain.binding.response.MeasurementResponseModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.service.api.MeasurementService;

@RestController
@RequestMapping(value = "/measurement", produces = MediaType.APPLICATION_JSON_VALUE)
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementCalculator measurementCalculator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementCalculator measurementCalculator) {
        this.measurementService = measurementService;
        this.measurementCalculator = measurementCalculator;
    }

    @PostMapping
    public ResponseEntity<MeasurementBindingModel> create(@RequestBody MeasurementBindingModel measurementBindingModel) {
        MeasurementServiceModel measurementServiceModel = MeasurementServiceModel.from(measurementBindingModel);
        measurementService.save(measurementServiceModel);
        return ResponseEntity.ok(measurementBindingModel);
    }

    @GetMapping(value = "/charts/hour")
    public ResponseEntity<List<DateWithMeasurementsResponseModel>>
           getMeasurementFor24Hours(AveragedMeasurementBidingModel averagedMeasurementBidingModel) {
        Map<LocalDateTime, MeasurementResponseModel> averagedMeasurements = measurementCalculator.calculateMeasurementsBetween(averagedMeasurementBidingModel.getStartPeriod(),
                                                                                                                               averagedMeasurementBidingModel.getEndPeriod(),
                                                                                                                               averagedMeasurementBidingModel.getRaspberryId());
        List<DateWithMeasurementsResponseModel> dateWithMeasurements = new ArrayList<>();
        averagedMeasurements.forEach((key, value) -> dateWithMeasurements.add(new DateWithMeasurementsResponseModel(key, value)));
        return ResponseEntity.ok(dateWithMeasurements);
    }

}
