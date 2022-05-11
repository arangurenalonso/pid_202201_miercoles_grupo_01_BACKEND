package com.system.backend.manage.building.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.dto.entrada.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.service.FamiliarService;

@RestController
@RequestMapping("/api/familiar")
public class FamiliarController {
	

	@Autowired
	private FamiliarService familiarService;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> registrar(@Valid@RequestBody FamiliarCreateDTO familiarCreateDTO,@PathVariable(name = "id") long id){
		System.out.println(">>>>>>>>>>>++++++>>>>>>>>>>>>>>>>>>>>>> Entro a Familiar Crear");
		Familiar familiarNuevo = familiarService.crearFamiliar(familiarCreateDTO,id);		
		ResponseDetails detalles = new ResponseDetails(200, "Se creo al familiar correctamente",familiarNuevo );
		Response response = new Response("Success","Se creo el familiar",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<?> actualizar(@Valid@RequestBody FamiliarCreateDTO familiarCreateDTO){
		Familiar familiarEditado = familiarService.editarFamiliar(familiarCreateDTO);		
		ResponseDetails detalles = new ResponseDetails(201, "El familiar con id " +familiarEditado.getId()+ " fue editado satisfactoriamente",familiarEditado );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id){
		Familiar familiar = familiarService.changeActive(id);
		
		ResponseDetails detalles = new ResponseDetails(200,familiar.getPersona().getEstado()?"Cambio Exitoso-> Familiar Activo":"Cambio Exitoso -> Familiar Inactiva",familiar );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
