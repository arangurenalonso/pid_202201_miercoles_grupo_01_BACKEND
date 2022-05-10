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
import com.system.backend.manage.building.dto.entrada.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.entrada.PropietarioDTO;
import com.system.backend.manage.building.dto.entrada.PropietarioUpdate;
import com.system.backend.manage.building.dto.entrada.UpdatePropietarioDepartamentosDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.jsonignore.PropietarioIgnoreProperties;
import com.system.backend.manage.building.jsonignore.PropietarioIncludePropetiesID;
import com.system.backend.manage.building.service.FamiliarService;
import com.system.backend.manage.building.service.PropietarioDepartamentoService;
import com.system.backend.manage.building.service.PropietarioService;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {


	@Autowired
	private PropietarioService propietarioService;
	@GetMapping("/all")
    public ResponseEntity<?>listarPropietarios(){
		List<PropietarioIgnoreProperties> lista = propietarioService.getAll();
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", lista);
		Response response = new Response("Success","Busqueda encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping("/buscarDepartamentos")
    public ResponseEntity<?>buscarDepartamentoXPropietario( @RequestBody PropietarioIncludePropetiesID prop){
		List<Departamento> lista = propietarioService.buscarDepartamentoXPropietario(prop.getId());
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", lista);
        Response response = new Response("Success","Busqueda encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	} 
	
	@GetMapping()
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir){
		PaginacionRespuesta paginacion = propietarioService.listaPropietarios(numeroDePagina, medidaDePagina, ordenarPor, sorDir);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", paginacion);
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
	public ResponseEntity<?> savePropietario(@Valid @RequestBody PropietarioDTO propietarioCreate){
		
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
	
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id){
		Propietario propietario = propietarioService.changeActive(id);
		
		ResponseDetails detalles = new ResponseDetails(200,propietario.getPersona().getEstado()?"Cambio Exitoso-> Propietario Activo":"Cambio Exitoso -> Propietario Inactiva",propietario );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
