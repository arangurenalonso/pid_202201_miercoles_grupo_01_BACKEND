package com.system.backend.manage.building.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

//import com.auth0.jwt.exceptions.AlgorithmMismatchException;

//import com.auth0.jwt.exceptions.InvalidClaimException;
//import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.backend.manage.building.dto.AuthTokenDTO;
import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.utils.AppConstantes;

@Component
public class GenerateToken {

	public AuthTokenDTO generateTokens(User user) {
		// String username = authentication.getName();
		// "secret"
		// It going to be the algorithm that I'm going to use to sign the JWT and the
		// refresh token

		Algorithm algorithm = Algorithm.HMAC256(AppConstantes.JWT_SECRET.getBytes());
		System.out.println("algorithm: " + algorithm);
		String access_token = JWT.create().withSubject(user.getUsername())
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(
						new Date(System.currentTimeMillis() + AppConstantes.JWT_ACCESS_TOKEN_EXPIRATION_MILLISECONDS))
				.withIssuer(user.getUsername())
				.withClaim("roles",
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		String refresh_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(
						new Date(System.currentTimeMillis() + AppConstantes.JWT_REFRESH_TOKEN_EXPIRATION_MILLISECONDS))
				.withIssuer(user.getUsername()).sign(algorithm);

		AuthTokenDTO tokens = new AuthTokenDTO(access_token, refresh_token);
		return tokens;
	}

	public AuthTokenDTO generateTokens(Usuario usuario) {
		Algorithm algorithm = Algorithm.HMAC256(AppConstantes.JWT_SECRET.getBytes());
		String access_token = JWT.create().withSubject(usuario.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))// 10 minutos
				.withIssuer(usuario.getUsername())
				.withClaim("roles", usuario.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
				.sign(algorithm);

		String refresh_token = JWT.create().withSubject(usuario.getUsername())
				.withExpiresAt(
						new Date(System.currentTimeMillis() + AppConstantes.JWT_REFRESH_TOKEN_EXPIRATION_MILLISECONDS))// 10
																														// minutos
				.withIssuer(usuario.getUsername()).sign(algorithm);

		AuthTokenDTO tokens = new AuthTokenDTO(access_token, refresh_token);
		return tokens;
	}

	// Bearer token de acceso
	public String getBearerTokend(HttpServletRequest request) {
		
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader == null)
			throw new CustomAppException("No se encontró el nombre del campo 'Authorization' dentro de las cabeceras",
					400, " header fieldname not found; Authorization request header is required",
					"NullPointerException", HttpStatus.BAD_REQUEST);
		if (!StringUtils.hasText(authorizationHeader)) {
			throw new CustomAppException("El campo Authorization está vacío",
					400, "Authorization Header is empty",
					"NullPointerException", HttpStatus.BAD_REQUEST);
		}
		if (!authorizationHeader.startsWith(AppConstantes.BEARER)) {
			throw new CustomAppException("No se encontró el nombre del campo 'Authorization' dentro de las cabeceras",
					400, " header fieldname not found; Authorization request header is required",
					"NullPointerException", HttpStatus.BAD_REQUEST);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> OK");
		return authorizationHeader.substring(AppConstantes.BEARER.length());
		
	}

	public DecodedJWT validarToken(String token, HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Validar Token");
		try {
			Algorithm algorithm = Algorithm.HMAC256(AppConstantes.JWT_SECRET.getBytes());
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(token);

			return decodedJWT;
		} catch (TokenExpiredException e) {

			throw new CustomAppException("El token ha expirado", 400, e.getMessage(), "TokenExpiredException",
					HttpStatus.BAD_REQUEST);

		} catch (JWTDecodeException e) {

			throw new CustomAppException("Error al decodificar el token: token no valido", 400, e.getMessage(),
					"TokenExpiredException", HttpStatus.BAD_REQUEST);

		}

		catch (Exception e) {
			response.setHeader("error", e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());
			Map<String, String> error = new HashMap<>();
			error.put("error_message", e.getMessage());
			error.put("error_type", e.toString());
			error.put("UBICACION DEL ERROR", "AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII EXEPTION GENERAL");
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			new ObjectMapper().writeValue(response.getOutputStream(), error);
		}
		return null;

	}
}