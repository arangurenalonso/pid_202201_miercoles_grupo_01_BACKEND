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
import com.system.backend.manage.building.dto.entrada.MascotaDTO;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.service.MascotaService;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
	

	@Autowired
	private MascotaService mascotaService;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> agregarMascota(@Valid@RequestBody MascotaDTO mascota,@PathVariable(name = "id") long id){
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$ Entro a salvar mascota");
		Mascota mascotaNueva = mascotaService.crearMascota(mascota,id);		
		ResponseDetails detalles = new ResponseDetails(200, "Se creo la mascota correctamente",mascotaNueva );
		Response response = new Response("Success","Se creo el propietario",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@PostMapping("/actualizar")
	public ResponseEntity<?> actualizar(@Valid@RequestBody MascotaDTO mascota){
		Mascota mascotaActualizada = mascotaService.actualizar(mascota);		
		ResponseDetails detalles = new ResponseDetails(201, "La mascota con id " +mascota.getId()+ " fue editado satisfactoriamente",mascotaActualizada );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id){
		Mascota mascota = mascotaService.changeActive(id);
		
		ResponseDetails detalles = new ResponseDetails(200,mascota.getIsActive()?"Cambio Exitoso-> Macota Activa":"Cambio Exitoso -> Mascota Inactiva",mascota );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
