package com.system.backend.manage.building.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.system.backend.manage.building.dto.entrada.DepartamentoDTO;
import com.system.backend.manage.building.dto.entrada.DepartamentoDTO.CreateDepartamento;
import com.system.backend.manage.building.dto.entrada.DepartamentoDTO.UpdatedDepartamento;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoControlador {

	@Autowired
	private DepartamentoService departamentoservice;

	@GetMapping
	public PaginacionRespuesta listarDepartamentos(
			@RequestParam(value = "numeroDePagina", defaultValue = PaginacionConstant.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "pageSize", defaultValue = PaginacionConstant.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = PaginacionConstant.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = PaginacionConstant.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
		
		return departamentoservice.obtenertodoslosDepartamento(numeroDePagina, medidaDePagina, ordenarPor, sortDir);

	}
	@GetMapping("/disponible")
	public List<Departamento> listaDepartamentoDisponibles(){
		return departamentoservice.listaDepartamentoDisponibles();

	}
	@GetMapping("/all")
	public List<Departamento> listaTodosDepartamnetos(){
		List<Departamento> departamentos= departamentoservice.listaTodosDepartamnetos();
		List<Departamento> departamentosSend= departamentos.stream().map(x->{
			Departamento dep=new Departamento();
			dep.setDepnumero(x.getDepnumero());
			dep.setId(x.getId());
			dep.setEstado(x.getEstado());
			return dep;
		}).collect(Collectors.toList());
		return departamentosSend;

	}
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerDepartametoPorId(@PathVariable(name = "id") long id) {
		Departamento departamento= departamentoservice.obtenerDepartamentosPorId(id);
		ResponseDetails detalles = new ResponseDetails(200, "Departamento con id "+id+" encontrado",departamento );
		Response response = new Response("Success","Busqueda Exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> guardarDepartamento( @Validated({ CreateDepartamento.class }) @RequestBody DepartamentoDTO departamentoDTO) {
		DepartamentoDTO departamentoRespuesta = departamentoservice.crearDepartamento(departamentoDTO);
		ResponseDetails detalles = new ResponseDetails(200, "Se creo el departamento", departamentoRespuesta);
		Response response = new Response("Success","Se creo el departamento",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarDepartamento(@Validated({ UpdatedDepartamento.class }) @RequestBody DepartamentoDTO departamentoDTO,
			@PathVariable(name = "id") long id) {

		DepartamentoDTO departamentoRespuesta = departamentoservice.actualizarDepartamento(departamentoDTO, id);

		ResponseDetails responseDetalle = new ResponseDetails(200, "Se actualizó el departamento",departamentoRespuesta );
		Response response = new Response("Success","Se actualizo el departamento",responseDetalle);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDepartamento(@PathVariable(name = "id") long id) {
		DepartamentoDTO eliminardepatamento = departamentoservice.eliminarDepartamento(id);
		
		ResponseDetails responseDetalle = new ResponseDetails(200, "Se elimino el departamento", eliminardepatamento);
		Response response = new Response("Success","Se encontro el departamento",responseDetalle);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@DeleteMapping("/changeActive/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id){
		Departamento departamento = departamentoservice.changeActive(id);		
		ResponseDetails detalles = new ResponseDetails(200,departamento.getEstado()?"Cambio Exitoso-> Departamento Activo":"Cambio Exitoso -> Departamento Inactiva",departamento );
		Response response = new Response("Success","Actualización exitosa",detalles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}