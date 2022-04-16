package com.system.backend.manage.building.controller;

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

import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.PropietarioDTO;
import com.system.backend.manage.building.dto.PropietarioRespuesta;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.utils.AppConstantes;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {
	@Autowired
	private PropietarioService propietarioService;

	@GetMapping()
    public ResponseEntity<?>listarPropietarios(
			@RequestParam(value = "PageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina,
			@RequestParam(value = "pageSize",defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sorDir){
        PropietarioRespuesta propietariorespuesta = propietarioService.listaPropietarios(numeroDePagina, medidaDePagina, ordenarPor, sorDir);
		
		ResponseDetails detalleRespuesta = new ResponseDetails(200, "Se encontro el listado correctamente ", propietariorespuesta);
        Response response = new Response();
        response.setType("Success");
        response.setDetalle(detalleRespuesta);
        response.setReason("Buscado encontrada");
		
        return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PropietarioDTO> obtenerPropietarioPorId(@PathVariable(name = "id") long id){
		
		
		return ResponseEntity.ok(propietarioService.obtenerPropietarioPorId(id));
		//
	}
	
	@PostMapping
	public ResponseEntity<?> savePropietario(@RequestBody PropietarioDTO propietarioDTO){
			
		PropietarioDTO propietarioRespuesta = propietarioService.savePropietario(propietarioDTO);
		System.out.println(">>>>>>>>>>>>>>>>crear propietario: " + propietarioRespuesta);
		if (propietarioRespuesta == null) {

			ResponseDetails errorDetalles = new ResponseDetails(401, "No se pudo crear",
					propietarioRespuesta);
			Response response = new Response();
			response.setType("Error");
			response.setDetalle(errorDetalles);
			response.setReason("Data no valida ");
			return new ResponseEntity<>(propietarioRespuesta, HttpStatus.NO_CONTENT);
		}
		ResponseDetails errorDetalles = new ResponseDetails(200, "Se creo el propietario", "");
		Response response = new Response();
		response.setType("Success");
		response.setDetalle(errorDetalles);
		response.setReason("Se creo el propietario");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//Devolver el propietario que se ha ingresado <?>
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPropietario(@RequestBody PropietarioDTO propietarioDTO,@PathVariable(name = "id") long id){
		PropietarioDTO propietarioRespuesta = propietarioService.actualizarPropietario(propietarioDTO, id);
		System.out.println(">>>>>>>>>>>>>>>>eliminar departamento: " + propietarioRespuesta);
		if (propietarioRespuesta == null) {

			ResponseDetails errorDetalles = new ResponseDetails(401, "No se pudo actualizar",
					propietarioRespuesta);
			Response response = new Response();
			response.setType("Error");
			response.setDetalle(errorDetalles);
			response.setReason("Data no valida ");
			return new ResponseEntity<>(propietarioRespuesta, HttpStatus.NO_CONTENT);
		}
		ResponseDetails errorDetalles = new ResponseDetails(200, "Se actualizó el propietario", "");
		Response response = new Response();
		response.setType("Success");
		response.setDetalle(errorDetalles);
		response.setReason("Se actualizo el propietario");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}// Devolver el propietario actualizado
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPropietario(@PathVariable(name = "id") long id){
		PropietarioDTO eliminarpropietario = propietarioService.eliminarPropietario(id);
		System.out.println(">>>>>>>>>>>>>>>>elininar departamento: " + eliminarpropietario);
		if (eliminarpropietario == null) {

			ResponseDetails errorDetalles = new ResponseDetails(401, "No se elimino correctamente",
					eliminarpropietario);
			Response response = new Response();
			response.setType("Error");
			response.setDetalle(errorDetalles);
			response.setReason("Data no valida ");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		ResponseDetails errorDetalles = new ResponseDetails(200, "Se elimino el departamento", "");
		Response response = new Response();
		response.setType("Success");
		response.setDetalle(errorDetalles);
		response.setReason("Se encontro el departamento");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}// Devolver el propietario cambiado de estado
	
	/*@GetMapping("/list")
	public ResponseEntity<Response> getUsers() {
		
		
		
		List<Propietario> lstPropietarios= propietarioService.listaPropietarios();
		if(lstPropietarios==null) {
			return null;
			
		}
		ResponseDetails resDetail=new ResponseDetails();
		resDetail.setHttpStatusCode(200);
		resDetail.setMensaje("Cantidad de Propietarios encontrado encontrados: "+lstPropietarios.size());
		resDetail.setData(lstPropietarios);
		
		Response res=new Response();
		res.setType("success");
		res.setReason("Se encontro la lista de Propietarios");
		res.setDetalle(resDetail);
		
		return new ResponseEntity<Response>(res,HttpStatus.OK);
	}
	
*/
	
}
