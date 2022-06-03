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

import com.system.backend.manage.building.dto.entrada.CancelarPagosDTO;
import com.system.backend.manage.building.dto.entrada.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.PagoServicio;
import com.system.backend.manage.building.service.FamiliarService;
import com.system.backend.manage.building.service.PagoServicioService;

@RestController
@RequestMapping("/api/pagoServicio")
public class PagoServicioController {
	

	@Autowired
	private PagoServicioService pagoServicioService;
	
	@PostMapping("/cancelar")
	public ResponseEntity<?> cancelar(@Valid @RequestBody CancelarPagosDTO familiarCreateDTO){
		PagoServicio pagoServicio = pagoServicioService.cancelarServicios(familiarCreateDTO);		
		ResponseDetails detalles = new ResponseDetails(200, "Se pagaron las boletas pendientes",pagoServicio );
		Response response = new Response("Success","Pago Realizado",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	
}
