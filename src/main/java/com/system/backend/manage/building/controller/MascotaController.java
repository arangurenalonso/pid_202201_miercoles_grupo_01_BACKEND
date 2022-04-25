package com.system.backend.manage.building.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.system.backend.manage.building.dto.MascotaCreateDTO;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.service.MascotaService;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
	

	@Autowired
	private MascotaService mascotaService;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> agregarMascota(@Valid@RequestBody MascotaCreateDTO mascotaCreateDTO,@PathVariable(name = "id") long id){
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$ Entro a salvar mascota");
		Mascota mascotaNueva = mascotaService.crearMascota(mascotaCreateDTO,id);		
		ResponseDetails detalles = new ResponseDetails(200, "Se creo la mascota correctamente",mascotaNueva );
		Response response = new Response("Success","Se creo el propietario",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	
}
