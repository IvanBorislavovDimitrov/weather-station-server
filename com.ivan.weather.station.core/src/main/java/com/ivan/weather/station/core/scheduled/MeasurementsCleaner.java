package com.ivan.weather.station.core.scheduled;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.ivan.weather.station.core.service.api.MeasurementService;
import com.ivan.weather.station.core.service.impl.MeasurementServiceImpl;

@Configuration
public class MeasurementsCleaner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementsCleaner(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @Scheduled(cron = "0 0 0/6 * * ?")
    public void removeOldMeasurements() {
        LocalDateTime fiveDaysAgo = LocalDateTime.now()
                                                  .minusDays(5);
        int deletedMeasurements = measurementService.deleteMeasurementsOlderThan(fiveDaysAgo);
        LOGGER.info(MessageFormat.format("Measurements deleted: \"{0}\"", deletedMeasurements));
    }
}
