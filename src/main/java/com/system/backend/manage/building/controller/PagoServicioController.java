package com.system.backend.manage.building.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.constant.PaginacionConstant;
import com.system.backend.manage.building.dto.entrada.CancelarPagosDTO;
import com.system.backend.manage.building.dto.entrada.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
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
	
	@GetMapping("/paginacion")
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir,
			@RequestParam(value = "departamento", defaultValue = "-1", required = false) long depId
    		){
		PaginacionRespuesta paginacion = pagoServicioService.paginacion(numeroDePagina, medidaDePagina, ordenarPor, sorDir,depId);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro la paginación ", paginacion);
        Response response = new Response("Success","Buscado encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
}
