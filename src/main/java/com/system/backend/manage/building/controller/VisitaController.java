package com.system.backend.manage.building.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.constant.PaginacionConstant;
import com.system.backend.manage.building.dto.entrada.VisitaDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.dto.salida.VisitaDTOSalida;
import com.system.backend.manage.building.entity.Visita;
import com.system.backend.manage.building.service.VisitaService;



@RestController
@RequestMapping("/api/visita")
public class VisitaController {
	@Autowired
	private VisitaService visitaService;
	
	@GetMapping("/page")
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir,
			@RequestParam(value = "filtroNombre", defaultValue = PaginacionConstant.FILTRO_POR_DEFECTO, required = false) String filtroNombre,
			@RequestParam(value = "filtroDNI", defaultValue = PaginacionConstant.FILTRO_POR_DEFECTO, required = false) String filtroDNI,
			@RequestParam(value = "estado", defaultValue = PaginacionConstant.ESTADO_VISITA_POR_DEFECTO, required = false) int estado
    		){
		
		PaginacionRespuesta paginacion = visitaService.listadoFiltroPaginacion(numeroDePagina, medidaDePagina, ordenarPor, sorDir,filtroNombre,filtroDNI,estado);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", paginacion);
        Response response = new Response("Success","Buscado encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar( @RequestBody VisitaDTO visita){
		
		Visita vis = visitaService.registrarVisita(visita);		
		ResponseDetails detalles = new ResponseDetails(200, "Se Registro la visita correctamente",vis );
		Response response = new Response("Success","Registro Exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/finalizarVisita")
	public ResponseEntity<?> finalizarVisita( @Valid@RequestBody VisitaDTO visita){
		
		Visita vis = visitaService.finalizarVisita(visita);		
		ResponseDetails detalles = new ResponseDetails(200, "Visita actualizada correctamente",vis );
		Response response = new Response("Success","Visita Finalizada con exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarVisita(@PathVariable(name = "id") long id){
		VisitaDTOSalida vis = visitaService.BuscarVisita(id);		
		ResponseDetails detalles = new ResponseDetails(200, "Visita encontrada",vis );
		Response response = new Response("Success","Busqueda Exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
