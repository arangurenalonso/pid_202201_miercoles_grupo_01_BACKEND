package com.system.backend.manage.building.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.system.backend.manage.building.dto.entrada.DepartamentoDTO;
import com.system.backend.manage.building.dto.entrada.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.entrada.DepartamentoDTO.CreateDepartamento;
import com.system.backend.manage.building.dto.entrada.EventoIncidenteDTO;
import com.system.backend.manage.building.dto.salida.EventoIncidenteDTOSalida;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.EventoIncidente;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.PagoServicio;
import com.system.backend.manage.building.service.EventoIncidenteService;
import com.system.backend.manage.building.service.FamiliarService;
import com.system.backend.manage.building.service.PagoServicioService;

@RestController
@RequestMapping("/api/eventoIncidente")
public class EventoIncidenteController {
	

	@Autowired
	private EventoIncidenteService eventoIncidenteService;


	@GetMapping("/paginacion")
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir,
			@RequestParam(value = "departamento", defaultValue = "-1", required = false) long depId,
			@RequestParam(value = "incidente", defaultValue = "-1", required = false) long inciID
    		){
		PaginacionRespuesta paginacion = eventoIncidenteService.paginacion(numeroDePagina, medidaDePagina, ordenarPor, sorDir,depId,inciID);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro la paginación ", paginacion);
        Response response = new Response("Success","Buscado encontrada",detalleRespuesta);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar( @RequestBody EventoIncidenteDTO obj) {
		EventoIncidenteDTOSalida objRegistrado = eventoIncidenteService.registrar(obj);
		ResponseDetails detalles = new ResponseDetails(200, "Incidente registrado exitosamente", objRegistrado);
		Response response = new Response("Success","Registro exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping("/actualizar_estado")
	public ResponseEntity<?> actualizarEstado( @RequestBody EventoIncidenteDTO obj) {
		EventoIncidenteDTOSalida objUpdate = eventoIncidenteService.statusUpdate(obj);
		ResponseDetails detalles = new ResponseDetails(200, "Estado del incidente actualizado exitosamente", objUpdate);
		Response response = new Response("Success","Actualización del estado exitoso",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
