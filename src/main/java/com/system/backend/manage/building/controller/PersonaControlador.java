package com.system.backend.manage.building.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.service.PersonaService;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonaControlador {

	@Autowired
	private PersonaService personaService;

	@GetMapping("/list")
	public ResponseEntity<?> register() {
		return new ResponseEntity<List<Persona>>(personaService.listPersona(), HttpStatus.OK);
	}

	
}
