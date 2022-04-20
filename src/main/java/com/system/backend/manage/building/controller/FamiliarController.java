package com.system.backend.manage.building.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.dto.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.service.FamiliarService;

@RestController
@RequestMapping("/api/familiar")
public class FamiliarController {
	

	@Autowired
	private FamiliarService familiarService;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> agregar( @RequestBody FamiliarCreateDTO familiarCreateDTO,@PathVariable(name = "id") long id){
		System.out.println(">>>>>>>>>>>++++++>>>>>>>>>>>>>>>>>>>>>> Entro a Familiar Crear");
		Familiar familiarNuevo = familiarService.crearFamiliar(familiarCreateDTO,id);		
		ResponseDetails detalles = new ResponseDetails(200, "Se creo al familiar correctamente",familiarNuevo );
		Response response = new Response("Success","Se creo el familiar",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	
}
