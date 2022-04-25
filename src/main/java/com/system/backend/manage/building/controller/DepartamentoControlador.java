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
import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.DepartamentoRespuesta;

import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoControlador {

	@Autowired
	private DepartamentoService departamentoservice;

	@GetMapping
	public DepartamentoRespuesta listarDepartamentos(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "pageSize", defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
		System.out.println("primer paso:" + ordenarPor);
		return departamentoservice.obtenertodoslosDepartamento(numeroDePagina, medidaDePagina, ordenarPor, sortDir);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerDepartametoPorId(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(departamentoservice.obtenerDepartamentosPorId(id));

	}

	@PostMapping
	public ResponseEntity<?> guardarDepartamento(@Valid @RequestBody DepartamentoDTO departamentoDTO) {
		DepartamentoDTO departamentoRespuesta = departamentoservice.crearDepartamento(departamentoDTO);
		System.out.println(">>>>>>>>>>>>>>>>crear departamento: " + departamentoRespuesta);
		if (departamentoRespuesta == null) {

			ResponseDetails errorDetalles = new ResponseDetails(401, "No se pudo crear",
					departamentoRespuesta);
			Response response = new Response();
			response.setType("Error");
			response.setDetalle(errorDetalles);
			response.setReason("Data no valida ");
			return new ResponseEntity<>(departamentoRespuesta, HttpStatus.NO_CONTENT);
		}
		ResponseDetails errorDetalles = new ResponseDetails(200, "Se creo el departamento", departamentoRespuesta);
		Response response = new Response();
		response.setType("Success");
		response.setDetalle(errorDetalles);
		response.setReason("Se creo el departamento");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarDepartamento(@RequestBody DepartamentoDTO departamentoDTO,
			@PathVariable(name = "id") long id) {

		DepartamentoDTO departamentoRespuesta = departamentoservice.actualizarDepartamento(departamentoDTO, id);
		System.out.println(">>>>>>>>>>>>>>>>elininar departamento: " + departamentoRespuesta);
		if (departamentoRespuesta == null) {

			ResponseDetails errorDetalles = new ResponseDetails(401, "No se pudo actualizar",
					departamentoRespuesta);
			Response response = new Response();
			response.setType("Error");
			response.setDetalle(errorDetalles);
			response.setReason("Data no valida ");
			return new ResponseEntity<>(departamentoRespuesta, HttpStatus.NO_CONTENT);
		}
		ResponseDetails errorDetalles = new ResponseDetails(200, "Se actualizó el departamento",departamentoRespuesta );
		Response response = new Response();
		response.setType("Success");
		response.setDetalle(errorDetalles);
		response.setReason("Se actualizo el departamento");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDepartamento(@PathVariable(name = "id") long id) {
		DepartamentoDTO eliminardepatamento = departamentoservice.eliminarDepartamento(id);
		System.out.println(">>>>>>>>>>>>>>>>elininar departamento: " + eliminardepatamento);
		if (eliminardepatamento == null) {

			ResponseDetails errorDetalles = new ResponseDetails(401, "No se elimino correctamente",
					eliminardepatamento);
			Response response = new Response();
			response.setType("Error");
			response.setDetalle(errorDetalles);
			response.setReason("Data no valida ");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		ResponseDetails errorDetalles = new ResponseDetails(200, "Se elimino el departamento", eliminardepatamento);
		Response response = new Response();
		response.setType("Success");
		response.setDetalle(errorDetalles);
		response.setReason("Se encontro el departamento");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}