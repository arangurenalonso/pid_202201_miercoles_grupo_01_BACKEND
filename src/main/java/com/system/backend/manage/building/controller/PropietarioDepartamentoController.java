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
import com.system.backend.manage.building.dto.PropietarioCreate;
import com.system.backend.manage.building.dto.PropietarioRespuesta;
import com.system.backend.manage.building.dto.PropietarioUpdate;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.dto.UpdatePropietarioDepartamentosDTO;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.PropietarioDepartamento;
import com.system.backend.manage.building.service.PropietarioDepartamentoService;
import com.system.backend.manage.building.service.PropietarioService;

@RestController
@RequestMapping("/api/propietariodepartamneto")
public class PropietarioDepartamentoController {
	@Autowired
	private PropietarioDepartamentoService propietarioDepartamentoService;
	@PostMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable(name = "id") long id_propietario,@Valid @RequestBody UpdatePropietarioDepartamentosDTO updatePropietarioDepartamentosDTO){
		
		List<PropietarioDepartamento> propietariosDepartamentos= propietarioDepartamentoService.actualizarDepartamentosPropietario(id_propietario,updatePropietarioDepartamentosDTO);
		
		return new ResponseEntity<>(propietariosDepartamentos, HttpStatus.OK);
	}
	
	
	
}
