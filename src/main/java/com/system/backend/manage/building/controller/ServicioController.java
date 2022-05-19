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
import com.system.backend.manage.building.dto.entrada.ServicioDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.dto.salida.ServicioDTOSalida;
import com.system.backend.manage.building.entity.Servicio;
import com.system.backend.manage.building.service.ServicioService;



@RestController
@RequestMapping("/api/servicio")
public class ServicioController {
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping("/paginacion")
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir
    		){
		PaginacionRespuesta paginacion = servicioService.paginacion(numeroDePagina, medidaDePagina, ordenarPor, sorDir);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", paginacion);
        Response response = new Response("Success","Buscado encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
		ServicioDTOSalida servicio= servicioService.BuscarPorID(id);
		ResponseDetails detalles = new ResponseDetails(200, "Servicio con id "+id+" encontrado",servicio );
		Response response = new Response("Success","Busqueda Exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@PostMapping()
	public ResponseEntity<?> registrar( @Valid @RequestBody ServicioDTO servicioDTO){
		
		Servicio servicio = servicioService.registrar(servicioDTO);		
		ResponseDetails detalles = new ResponseDetails(200, "Servicio registrado correctamente",servicio );
		Response response = new Response("Success","Registro Exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@PutMapping()
	public ResponseEntity<?> actualizar(@Valid @RequestBody ServicioDTO servicioDTO) {

		Servicio servicio = servicioService.actualizar(servicioDTO);		

		ResponseDetails responseDetalle = new ResponseDetails(200, "Servicio actualizado correctamento",servicio );
		Response response = new Response("Success","Actualización exitosa",responseDetalle);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> cambiarEstado(@PathVariable(name = "id") long id){
		Servicio servicio = servicioService.changeActive(id);
		
		ResponseDetails detalles = new ResponseDetails(200,servicio.getIsActive()?"Cambio Exitoso-> Servicio Activo":"Cambio Exitoso -> Servicio Inactiva",servicio );
		Response response = new Response("Success","Cambio de estado exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
