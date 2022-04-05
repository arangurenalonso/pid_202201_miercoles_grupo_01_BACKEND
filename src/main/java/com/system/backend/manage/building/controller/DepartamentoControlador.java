package com.system.backend.manage.building.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoControlador {
	
	@Autowired
	private DepartamentoService departamentoservice;
	@PostMapping
	public ResponseEntity<DepartamentoDTO>guardarDepartamento(@RequestBody DepartamentoDTO departamentoDTO){
	return new ResponseEntity<>(departamentoservice.crearDepartamento(departamentoDTO),HttpStatus.CREATED);
		
	}
	@GetMapping
	public List<DepartamentoDTO>listarDepartamento(){
		return departamentoservice.obtenertodoslosDepartamento();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartamentoDTO>obtenerDepartamentoPorId(@PathVariable(name="idDepartamento")long idDepartamento){
		
		return ResponseEntity.ok(departamentoservice.obtenerDepartamentosPorId(idDepartamento));
	
}
}
