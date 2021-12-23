package com.ivan.weather.station.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.service.api.AnomalyDetectionRuleService;

@RestController
@RequestMapping(value = "/detection", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AnomalyDetectionRuleController {

    private final AnomalyDetectionRuleService anomalyDetectionRuleService;

    @Autowired
    public AnomalyDetectionRuleController(AnomalyDetectionRuleService anomalyDetectionRuleService) {
        this.anomalyDetectionRuleService = anomalyDetectionRuleService;
    }

    @GetMapping
    public ResponseEntity<List<AnomalyDetectionRuleResponseModel>> getAll() {
        List<AnomalyDetectionRuleServiceModel> anomalyDetectionRuleServiceModels = anomalyDetectionRuleService.findAll();
        return ResponseEntity.ok(anomalyDetectionRuleServiceModels.stream()
                                                                  .map(AnomalyDetectionRuleServiceModel::toResponseModel)
                                                                  .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<AnomalyDetectionRuleResponseModel>
           add(@RequestBody AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
        AnomalyDetectionRuleType anomalyDetectionRuleType = AnomalyDetectionRuleType.from(anomalyDetectionRuleBindingModel.getType());
        AnomalyDetectionRuleServiceModel anomalyDetectionRuleServiceModel = anomalyDetectionRuleType.getRuleParser(anomalyDetectionRuleBindingModel)
                                                                                                    .parse();
        anomalyDetectionRuleService.save(anomalyDetectionRuleServiceModel);
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = anomalyDetectionRuleServiceModel.toResponseModel();
        return ResponseEntity.ok(anomalyDetectionRuleResponseModel);
    }
}
