package com.system.backend.manage.building.controller;



import java.util.List;

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
import com.system.backend.manage.building.dto.entrada.IncidenteDTO;
import com.system.backend.manage.building.dto.salida.IncidenteDTOSalida;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.Incidente;
import com.system.backend.manage.building.service.IncidenteService;



@RestController
@RequestMapping("/api/incidente")
public class IncidenteController {
	@Autowired
	private IncidenteService incidenteService;
	
	@GetMapping("/paginacion")
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir
    		){
		PaginacionRespuesta paginacion = incidenteService.paginacion(numeroDePagina, medidaDePagina, ordenarPor, sorDir);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", paginacion);
        Response response = new Response("Success","Buscado encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/all")
    public ResponseEntity<?>seleccionarTodo(){
		List<Incidente> lista = incidenteService.getAll();
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", lista);
        Response response = new Response("Success","Buscado encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
		IncidenteDTOSalida incidente= incidenteService.BuscarPorID(id);
		ResponseDetails detalles = new ResponseDetails(200, "Incidente con id "+id+" encontrado",incidente );
		Response response = new Response("Success","Busqueda Exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@PostMapping()
	public ResponseEntity<?> agregar( @Valid @RequestBody IncidenteDTO incidenteDTO){
		
		Incidente incidente = incidenteService.registrar(incidenteDTO);		
		ResponseDetails detalles = new ResponseDetails(200, "Incidente registrado correctamente",incidente );
		Response response = new Response("Success","Registro Exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody IncidenteDTO incidenteDTO) {

		Incidente incidente = incidenteService.actualizar(incidenteDTO);		

		ResponseDetails responseDetalle = new ResponseDetails(200, "Incidente actualizado correctamento",incidente );
		Response response = new Response("Success","Actualización exitosa",responseDetalle);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id){
		Incidente incidente = incidenteService.changeActive(id);
		
		ResponseDetails detalles = new ResponseDetails(200,incidente.getIsActive()?"Cambio Exitoso-> Incidente Activo":"Cambio Exitoso -> Incidente Inactivo",incidente );
		Response response = new Response("Success","Cambio de estado exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
