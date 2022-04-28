package com.system.backend.manage.building.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.dto.VisitanteDTO;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Visitante;
import com.system.backend.manage.building.service.VisitanteService;



@RestController
@RequestMapping("/api/visitante")
public class VisitanteController {
	@Autowired
	private VisitanteService visitanteService;
	
	
	@GetMapping
	public ResponseEntity<?> listarTodo() {
		List<Visitante>visitantes= visitanteService.listarTodos();
		ResponseDetails detalles = new ResponseDetails(200, "Lista de Visitantes",visitantes );
		Response response = new Response("Success","Listado Obtenedio correctamente",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> agregar( @Valid@RequestBody VisitanteDTO visitanteCrear){
		
		Visitante visitanteCreado = visitanteService.crearVisitante(visitanteCrear);		
		ResponseDetails detalles = new ResponseDetails(200, "Se creo el visitante correctamente",visitanteCreado );
		Response response = new Response("Success","Se creo el visitante",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id){
		Visitante visitante = visitanteService.changeActive(id);
		
		ResponseDetails detalles = new ResponseDetails(200,visitante.getPersona().getEstado()?"Cambio Exitoso-> Visitante Activo":"Cambio Exitoso -> Visitante Inactiva",visitante );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
