package com.system.backend.manage.building.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.backend.manage.building.dto.RoleUserDTO;
import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.service.UsuarioService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Role> saveRole(@Valid@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());

		return ResponseEntity.created(uri).body(usuarioService.saveRole(role));
	}
	
	@PostMapping("/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserDTO form) {
		usuarioService.addRoleToUsuario(form.getUserName(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
}
