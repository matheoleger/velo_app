package com.formation.velo.controllers;

import com.formation.velo.model.Stations;
import com.formation.velo.service.StationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class DefibrillatorController {

    private final StationsService stationsService;

	public DefibrillatorController(StationsService stationsService) {
		this.stationsService = stationsService;
	}

    @GetMapping("defibrillators")
	public ResponseEntity<List<Stations>> getAll(){
		stationsService.saveRecords();
		List<Stations> stations = stationsService.findAll();

		return ResponseEntity.ok(stations);
	}
}
