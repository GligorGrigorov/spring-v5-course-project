package com.meals.mealsapp.scheduling;

import com.meals.mealsapp.service.MetricsService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class UpdateMetricsJob implements Job {
    private static final Logger LOGGER = getLogger(UpdateMetricsJob.class);

    private final MetricsService metricsService;

    @Autowired
    public UpdateMetricsJob(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    public void execute(JobExecutionContext jobExecutionContext) {
        metricsService.updateAll();
    }

}