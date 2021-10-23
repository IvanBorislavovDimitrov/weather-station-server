package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.core.calculator.MeasurementCalculator;
import com.ivan.weather.station.core.domain.binding.request.AveragedMeasurementRequestModel;
import com.ivan.weather.station.core.domain.binding.request.MeasurementRequestBindingModel;
import com.ivan.weather.station.core.domain.binding.response.DateWithMeasurementsResponseModel;
import com.ivan.weather.station.core.domain.binding.response.MeasurementResponseModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.service.api.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping(value = "/charts/hour")
    public ResponseEntity<List<DateWithMeasurementsResponseModel>> getMeasurementFor24Hours(AveragedMeasurementRequestModel averagedMeasurementRequestModel) {
        Map<LocalDateTime, MeasurementResponseModel> averagedMeasurements =
                measurementCalculator.calculateMeasurementsBetween(averagedMeasurementRequestModel.getStartPeriod(),
                        averagedMeasurementRequestModel.getEndPeriod(),
                        averagedMeasurementRequestModel.getRaspberryId());
        List<DateWithMeasurementsResponseModel> dateWithMeasurements = new ArrayList<>();
        averagedMeasurements.forEach((key, value) -> dateWithMeasurements.add(new DateWithMeasurementsResponseModel(key, value)));
        return ResponseEntity.ok(dateWithMeasurements);
    }

}
