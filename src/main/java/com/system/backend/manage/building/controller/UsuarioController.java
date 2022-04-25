package com.system.backend.manage.building.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.UsuarioService;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<Response> getUsers() {
		
		List<Usuario> lstUsuario= usuarioService.getUsers();
		
		ResponseDetails resDetail=new ResponseDetails();
		resDetail.setHttpStatusCode(200);
		resDetail.setMensaje("Cantidad de Usuario encontrados: "+lstUsuario.size());
		resDetail.setData(lstUsuario);
		
		Response res=new Response();
		res.setType("success");
		res.setReason("Se encontro la lista de usuarios");
		res.setDetalle(resDetail);
		
		return new ResponseEntity<Response>(res,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Usuario> saveUser(@Valid@RequestBody Usuario usuario) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
		return ResponseEntity.created(uri).body(usuarioService.saveUser(usuario));
	}
	
	 @PostMapping("/add")
	    public ResponseEntity<?> addNewUser(@RequestParam("firstName") String firstName,
	                                           @RequestParam("lastName") String lastName,
	                                           @RequestParam("dni") String dni,
	                                           @RequestParam("email") String email,
	                                           @RequestParam("password") String password) {
	        Usuario newUser = usuarioService.addNewUser(firstName, lastName,dni, email,password);
	        //role, isNonLocked, Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive)
	        return new ResponseEntity<>(newUser, HttpStatus.OK);
	    }
	
}
