package com.system.backend.manage.building.controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.system.backend.manage.building.dto.AuthTokenDTO;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.security.GenerateToken;
import com.system.backend.manage.building.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GenerateToken generateToken;

	@PostMapping("/register")
	public ResponseEntity<Usuario> register(@RequestBody Usuario usuario) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
		return ResponseEntity.created(uri).body(usuarioService.saveUser(usuario));
	}

	@PostMapping("/token/refresh")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {
		String token = generateToken.getBearerTokend(request);
		
		DecodedJWT decodedJWT = generateToken.validarToken(token, response);
		String username = decodedJWT.getSubject();
		
		
		Usuario user = usuarioService.getUser(username);
		AuthTokenDTO tokens = generateToken.generateTokens(user);
		
		ResponseDetails resDet=new ResponseDetails();
		resDet.setCodigoEstado(200);
		resDet.setMensaje("Actualizacion exitosa");
		resDet.setData(tokens);
		
		Response res=new Response ();
		res.setType("success");
		res.setMessage("Actualización del token exitosa");
		res.setDetalle(resDet);
		
		return new ResponseEntity<Response>(res, HttpStatus.OK);

	}
}
