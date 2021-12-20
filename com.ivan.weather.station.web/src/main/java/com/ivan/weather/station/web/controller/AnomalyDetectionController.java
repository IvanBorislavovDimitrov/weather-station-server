package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.service.api.AnomalyDetectionRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/detection", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AnomalyDetectionController {

    private final AnomalyDetectionRuleService anomalyDetectionRuleService;

    @Autowired
    public AnomalyDetectionController(AnomalyDetectionRuleService anomalyDetectionRuleService) {
        this.anomalyDetectionRuleService = anomalyDetectionRuleService;
    }

    @GetMapping
    public ResponseEntity<List<AnomalyDetectionRuleResponseModel>> getAll() {
        List<AnomalyDetectionRuleServiceModel> anomalyDetectionRuleServiceModels = anomalyDetectionRuleService.findAll();
        return ResponseEntity.ok(anomalyDetectionRuleServiceModels.stream()
                .map(AnomalyDetectionRuleServiceModel::toResponseModel)
                .collect(Collectors.toList()));
    }
}
