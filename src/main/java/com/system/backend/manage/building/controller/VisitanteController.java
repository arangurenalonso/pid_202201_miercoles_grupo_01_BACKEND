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
import com.system.backend.manage.building.dto.entrada.VisitanteDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.Visitante;
import com.system.backend.manage.building.service.VisitanteService;



@RestController
@RequestMapping("/api/visitante")
public class VisitanteController {
	@Autowired
	private VisitanteService visitanteService;
	
	@GetMapping("/paginacion")
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir,
			@RequestParam(value = "filtro", defaultValue = PaginacionConstant.FILTRO_POR_DEFECTO, required = false) String filtro,
			@RequestParam(value = "filtroBy", defaultValue = PaginacionConstant.FILTRO_POR_DEFECTO, required = false) String filtroBy
    		){
		System.out.println("ENTRO CORRECTAMENTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<");
		PaginacionRespuesta vistantesPage = visitanteService.paginacion(numeroDePagina, medidaDePagina, ordenarPor, sorDir,filtro,filtroBy);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", vistantesPage);
        Response response = new Response("Success","Buscado encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping
	public ResponseEntity<?> listarTodo() {
		List<Visitante>visitantes= visitanteService.listarTodos();
		ResponseDetails detalles = new ResponseDetails(200, "Lista de Visitantes",visitantes );
		Response response = new Response("Success","Listado Obtenedio correctamente",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
		VisitanteDTO visitantes= visitanteService.BuscarPorID(id);
		ResponseDetails detalles = new ResponseDetails(200, "Visitante con id "+id+" encontrado",visitantes );
		Response response = new Response("Success","Busqueda Exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody VisitanteDTO visitante,
			@PathVariable(name = "id") long id) {

		Visitante visitanteUpdate = visitanteService.actualizar(visitante, id);

		ResponseDetails responseDetalle = new ResponseDetails(200, "Se actualizó el Visitante",visitanteUpdate );
		Response response = new Response("Success","Se actualizo el Visitante",responseDetalle);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<?> agregar( @Valid@RequestBody VisitanteDTO visitanteCrear){
		
		Visitante visitanteCreado = visitanteService.crearVisitante(visitanteCrear);		
		ResponseDetails detalles = new ResponseDetails(200, "Se creo el visitante correctamente",visitanteCreado );
		Response response = new Response("Success","Se creo el visitante",detalles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id){
		Visitante visitante = visitanteService.changeActive(id);
		
		ResponseDetails detalles = new ResponseDetails(200,visitante.getPersona().getEstado()?"Cambio Exitoso-> Visitante Activo":"Cambio Exitoso -> Visitante Inactiva",visitante );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
