package com.system.backend.manage.building.controller;

import java.util.List;

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

import com.system.backend.manage.building.dto.ServiciosDTO;
import com.system.backend.manage.building.service.ServicioService;

@RestController
@RequestMapping("/api/servicio")

public class ServicioController {
	
	
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping
	public List<ServiciosDTO> listarServicios(@RequestParam(value= "numpag",defaultValue="0", required = false)int numeroDePagina,
			@RequestParam(value= "pageSize",defaultValue="10", required = false)int medidaDePagina){
	
	return servicioService.obtenerTodosLosServicios(numeroDePagina, medidaDePagina);
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ServiciosDTO> obtenerServicioPorID(@PathVariable(name = "id") long id){
	
		return ResponseEntity.ok(servicioService.obtenerServicioPorID(id));
		
	}
	
	@PostMapping
	public ResponseEntity<ServiciosDTO> guardarServicio(@RequestBody ServiciosDTO servicioDTO ){
		
		return new ResponseEntity<>(servicioService.crearServicio(servicioDTO),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ServiciosDTO> actualizarServicio(@RequestBody ServiciosDTO servicioDTO,@PathVariable(name = "id") long id){
		
		ServiciosDTO servicioRespuesta = servicioService.actualizarServicio(servicioDTO, id);
		
		return new ResponseEntity<>(servicioRespuesta,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ServiciosDTO> eliminarServicio(@PathVariable(name = "id") long id) {
		ServiciosDTO eliminarservicio = servicioService.eliminarServicio(id);
		
		return new ResponseEntity<ServiciosDTO>(eliminarservicio, HttpStatus.OK);
	}

}
