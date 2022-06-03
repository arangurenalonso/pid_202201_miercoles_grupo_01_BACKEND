package com.system.backend.manage.building.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.system.backend.manage.building.dto.entrada.BoletaServicioDTO;
import com.system.backend.manage.building.dto.salida.BoletaServicioDTOSalida;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.BoletaServicio;
import com.system.backend.manage.building.service.BoletaServicioService;



@RestController
@RequestMapping("/api/programacionPago")
public class BoletaServicioController {
	@Autowired
	private BoletaServicioService programacionPagoService;
	
	@PostMapping("/registrarPagos")
	public ResponseEntity<?> registrar( @Valid @RequestBody BoletaServicioDTO programacionPagosDTO){
		
		List<BoletaServicio> ppLista = programacionPagoService.registrarPagos(programacionPagosDTO);		
		ResponseDetails detalles = new ResponseDetails(200, "Programacion de Pagos Exitosa",ppLista );
		Response response = new Response("Success","Registro Exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/PagosPendientes/{id}")
	public ResponseEntity<?> pagosPendites( @PathVariable(name = "id") long id){
		
		List<BoletaServicioDTOSalida> ppLista = programacionPagoService.listarPagosPorPagarPorDepartamento(id);		
		ResponseDetails detalles = new ResponseDetails(200, "Pagos Pendientes",ppLista );
		Response response = new Response("Success","Lista Obtenido",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
