package com.system.backend.manage.building.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.service.UsuarioService;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {
	@Autowired
	private PropietarioService propietarioService;

	@GetMapping("/list")
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
	

	
}
