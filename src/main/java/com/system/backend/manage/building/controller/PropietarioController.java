package com.system.backend.manage.building.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.system.backend.manage.building.dto.PropietarioCreate;
import com.system.backend.manage.building.dto.PropietarioRespuesta;
import com.system.backend.manage.building.dto.PropietarioUpdate;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.service.PropietarioService;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {
	@Autowired
	private PropietarioService propietarioService;

	@GetMapping()
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "PageNo", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir){
        PropietarioRespuesta propietariorespuesta = propietarioService.listaPropietarios(numeroDePagina, medidaDePagina, ordenarPor, sorDir);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", propietariorespuesta);
        Response response = new Response();
        response.setType("Success");
        response.setDetalle(detalleRespuesta);
        response.setReason("Buscado encontrada");
		
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPropietarioPorId(@PathVariable(name = "id") long id){
		Propietario propietario=propietarioService.obtenerPropietarioPorId(id);
		ResponseDetails detalle = new ResponseDetails(200, "Propietario Encontrado", propietario);
		Response response = new Response("Success","Propietario encontrado",detalle);
		return new ResponseEntity<>(response, HttpStatus.OK);
		//
	}
	
	@PostMapping
	public ResponseEntity<?> savePropietario(@Valid @RequestBody PropietarioCreate propietarioCreate){
		
		Propietario propietarioRespuesta = propietarioService.savePropietario(propietarioCreate);		
		ResponseDetails errorDetalles = new ResponseDetails(200, "Se creo el propietario", propietarioService.obtenerPropietarioPorId(propietarioRespuesta.getId()));
		Response response = new Response("Success","Se creo el propietario",errorDetalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarDatosPropietarios(@Valid @RequestBody PropietarioUpdate propietarioUpdate,@PathVariable(name = "id") long id){
		Propietario propietarioActualizado = propietarioService.actualizarPropietario(propietarioUpdate, id);
		ResponseDetails detalle = new ResponseDetails(200, "Propietario Actualizado", propietarioActualizado);
		Response response = new Response("Success","Actualización correcta",detalle);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPropietario(@PathVariable(name = "id") long id){
		Propietario eliminarpropietario = propietarioService.eliminarPropietario(id);
		ResponseDetails detalle = new ResponseDetails(200, "Se elimino el departamento", eliminarpropietario);
		Response response = new Response("Success","Se encontro el departamento",detalle);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
}
