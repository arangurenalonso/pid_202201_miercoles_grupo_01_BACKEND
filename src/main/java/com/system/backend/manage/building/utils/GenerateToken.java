package com.system.backend.manage.building.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

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
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.excepciones.CustomAppException;

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
				.withClaim("roles", usuario.getPermiso().stream().map(permiso->{
					return permiso.getRole().getName();
				}).collect(Collectors.toList()))
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
	public String getBearerTokend(HttpServletRequest request, HttpServletResponse response) {
		
		String authorizationHeader = request.getHeader("Authorization");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> authorizationHeader: "+authorizationHeader);
		if (authorizationHeader == null) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entro al null: ");
			SecurityContextHolder.clearContext();
			throw new CustomAppException("Es necesario especificar el campo 'Authorization' dentro de las cabeceras",
					401, " header fieldname not found; Authorization request header is required",
					"NullPointerException", HttpStatus.UNAUTHORIZED);
		}
			
		if (!StringUtils.hasText(authorizationHeader)) {
			SecurityContextHolder.clearContext();
			throw new CustomAppException("La solicitud de Authorización debe de empezar con las palabras 'Bearer '",
					400, " Authorizatio request must starts with 'Bearer'",
					"NullPointerException", HttpStatus.BAD_REQUEST);
		}
		if (!authorizationHeader.startsWith(AppConstantes.BEARER)) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entro a que le falta el bearer");
	
			throw new CustomAppException("La solicitud de Authorización debe de empezar con las palabras 'Bearer '",
					400, " Authorizatio request must starts with 'Bearer'",
					"NullPointerException", HttpStatus.BAD_REQUEST);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> OK");
		return authorizationHeader.substring(AppConstantes.BEARER.length());
		
	}

	public DecodedJWT validarToken(String token, HttpServletRequest request, HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Validar Token");
		try {
			Algorithm algorithm = Algorithm.HMAC256(AppConstantes.JWT_SECRET.getBytes());
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(token);
			String username = decodedJWT.getSubject();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> USUARIO OBTENIDO: "+username);
			return decodedJWT;
		} catch (TokenExpiredException e) {
			SecurityContextHolder.clearContext();
			throw new CustomAppException("El token ha expirado", 401, e.getMessage(), "TokenExpiredException",
					HttpStatus.UNAUTHORIZED);

		} catch (JWTDecodeException e) {
			SecurityContextHolder.clearContext();
			throw new CustomAppException("Error al decodificar el token: token no valido", 401, e.getMessage(),
					"TokenExpiredException", HttpStatus.UNAUTHORIZED);

		}
		catch (Exception e) {
			SecurityContextHolder.clearContext();
			//SecurityContextHolder.clearContext();
			throw new CustomAppException(">>>>>>>>>>>Generate Token Exeption: "+e, 401, e.getMessage(),
					"Exception", HttpStatus.UNAUTHORIZED);
		}

	}
}