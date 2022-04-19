package com.system.backend.manage.building.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.constant.PaginacionConstant;
import com.system.backend.manage.building.dto.MascotaCreateDTO;
import com.system.backend.manage.building.dto.PropietarioCreate;
import com.system.backend.manage.building.dto.PropietarioRespuesta;
import com.system.backend.manage.building.dto.PropietarioUpdate;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.service.MascotaService;
import com.system.backend.manage.building.service.PropietarioService;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
	

	@Autowired
	private MascotaService mascotaService;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> agregarMascota( @RequestBody MascotaCreateDTO mascotaCreateDTO,@PathVariable(name = "id") long id){
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$ Entro a salvar mascota");
		Mascota mascotaNueva = mascotaService.crearMascota(mascotaCreateDTO,id);		
		ResponseDetails detalles = new ResponseDetails(200, "Se creo la mascota correctamente",mascotaNueva );
		Response response = new Response("Success","Se creo el propietario",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	
}
