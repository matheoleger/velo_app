package com.formation.velo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.formation.velo.service.StationsService;
import com.formation.velo.service.DefibrillatorService;

import lombok.extern.java.Log;

@Log
@Component
public class ScheduledTask {
    private final StationsService stationsService;
    private final DefibrillatorService defibrillatorService;

    public ScheduledTask(StationsService stationsService, DefibrillatorService defibrillatorService) {
        this.stationsService = stationsService;
        this.defibrillatorService = defibrillatorService;
    }

    @Scheduled(fixedRate = 60000)
    public void searchNextMatchByCompetition() {
        log.info("update stations and defibrillators");
        stationsService.saveRecords();
        defibrillatorService.saveRecords();
    }
}
