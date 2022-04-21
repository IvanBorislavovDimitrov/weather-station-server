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
@RequestMapping(value = "/detection", produces = MediaType.APPLICATION_JSON_VALUE)
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
        AnomalyDetectionRuleServiceModel anomalyDetectionRuleServiceModel = anomalyDetectionRuleType.getRuleBindingParser(anomalyDetectionRuleBindingModel)
                                                                                                    .parse();
        anomalyDetectionRuleService.save(anomalyDetectionRuleServiceModel);
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = anomalyDetectionRuleServiceModel.toResponseModel();
        return ResponseEntity.ok(anomalyDetectionRuleResponseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnomalyDetectionRuleResponseModel>
           edit(@PathVariable String id, @RequestBody AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
        AnomalyDetectionRuleType anomalyDetectionRuleType = AnomalyDetectionRuleType.from(anomalyDetectionRuleBindingModel.getType());
        AnomalyDetectionRuleServiceModel anomalyDetectionRuleServiceModel = anomalyDetectionRuleType.getRuleBindingParser(anomalyDetectionRuleBindingModel)
                                                                                                    .parse();
        anomalyDetectionRuleServiceModel.setId(id);
        anomalyDetectionRuleService.update(anomalyDetectionRuleServiceModel);
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = anomalyDetectionRuleServiceModel.toResponseModel();
        return ResponseEntity.ok(anomalyDetectionRuleResponseModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnomalyDetectionRuleResponseModel> get(@PathVariable String id) {
        AnomalyDetectionRuleServiceModel anomalyDetectionRuleServiceModel = anomalyDetectionRuleService.findById(id);
        return ResponseEntity.ok(anomalyDetectionRuleServiceModel.toResponseModel());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        anomalyDetectionRuleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/raspberry/{raspberryId}")
    public ResponseEntity<List<AnomalyDetectionRuleResponseModel>> getAnomaliesByRaspberry(@PathVariable String raspberryId) {
        List<AnomalyDetectionRuleServiceModel> anomalyDetectionRuleServiceModels = anomalyDetectionRuleService.findByRaspberryId(raspberryId);
        return ResponseEntity.ok(anomalyDetectionRuleServiceModels.stream()
                                                                  .map(AnomalyDetectionRuleServiceModel::toResponseModel)
                                                                  .collect(Collectors.toList()));

    }

}
