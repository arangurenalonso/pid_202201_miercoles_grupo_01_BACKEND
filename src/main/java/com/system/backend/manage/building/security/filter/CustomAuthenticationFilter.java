package com.system.backend.manage.building.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.backend.manage.building.dto.AuthTokenDTO;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.utils.GenerateToken;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private GenerateToken generateToken;

	private AuthenticationManager authenticationManager;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager, GenerateToken generateToken) {
		this.authenticationManager = authenticationManager;
		this.generateToken = generateToken;

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);
			
			return authenticationManager.authenticate(authenticationToken);

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Authenticacion exitosa");
		User user = (User) authentication.getPrincipal();
		AuthTokenDTO tokens = generateToken.generateTokens(user);
		Map<String, Object> responseToken = new HashMap<>();
		responseToken.put("tokens", tokens);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), responseToken);
	}
	@Autowired
    //@Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Authenticacion Fallida");
		 resolver.resolveException(request, response, null, new CustomAppException("La solicitud de Authorización debe de empezar con las palabras 'Bearer '",
					400, " Authorizatio request must starts with 'Bearer'",
					"NullPointerException", HttpStatus.BAD_REQUEST));
		
	}

	
	
}
