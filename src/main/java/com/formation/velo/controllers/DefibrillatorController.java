package com.formation.velo.controllers;

import com.formation.velo.model.Defibrillator;
import com.formation.velo.service.DefibrillatorService;
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

    private final DefibrillatorService defibrillatorService;

	public DefibrillatorController(DefibrillatorService defibrillatorService) {
		this.defibrillatorService = defibrillatorService;
	}

    @GetMapping("defibrillators")
	public ResponseEntity<List<Defibrillator>> getAll(){
		defibrillatorService.saveRecords();
		List<Defibrillator> defibrillator = defibrillatorService.findAll();

		return ResponseEntity.ok(defibrillator);
	}
}
