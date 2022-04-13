package com.system.backend.manage.building.controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.system.backend.manage.building.constant.SecurityConstant;
import com.system.backend.manage.building.dto.AuthTokenDTO;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.dto.UsuarioLoginDTO;
import com.system.backend.manage.building.entity.UserPrincipal;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.UsuarioService;
import com.system.backend.manage.building.utils.GenerateToken;
import com.system.backend.manage.building.utils.JWTTokenProvider;

import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/login")
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

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@PostMapping("/iniciarSesion")
	public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginDTO user) {
		System.out.println("==========================="+user);
		authenticate(user.getUsername(), user.getPassword());
		Usuario loginUser = usuarioService.getUser(user.getUsername());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	private HttpHeaders getJwtHeader(UserPrincipal user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
		return headers;
	}

	@PostMapping("/token/refresh")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {
		
		String token = generateToken.getBearerTokend(request,response);

		DecodedJWT decodedJWT = generateToken.validarToken(token, request, response);
		String username = decodedJWT.getSubject();

		Usuario user = usuarioService.getUser(username);
		AuthTokenDTO tokens = generateToken.generateTokens(user);

		ResponseDetails resDet = new ResponseDetails();
		resDet.setHttpStatusCode(200);
		resDet.setMensaje("Actualizacion exitosa");
		resDet.setData(tokens);

		Response res = new Response();
		res.setType("success");
		res.setReason("Actualización del token exitosa");
		res.setDetalle(resDet);

		return new ResponseEntity<Response>(res, HttpStatus.OK);

	}
}
