package com.system.backend.manage.building.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.system.backend.manage.building.dto.entrada.ProgramacionPagosDTO;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.ProgramacionPagos;
import com.system.backend.manage.building.service.ProgramacionPagosService;



@RestController
@RequestMapping("/api/programacionPago")
public class ProgramacionPagoController {
	@Autowired
	private ProgramacionPagosService programacionPagoService;
	
	@PostMapping("/registrarPagos")
	public ResponseEntity<?> registrar( @Valid @RequestBody ProgramacionPagosDTO programacionPagosDTO){
		
		List<ProgramacionPagos> ppLista = programacionPagoService.registrarPagos(programacionPagosDTO);		
		ResponseDetails detalles = new ResponseDetails(200, "Programacion de Pagos Exitosa",ppLista );
		Response response = new Response("Success","Registro Exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
}
