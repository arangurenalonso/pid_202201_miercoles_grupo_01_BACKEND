package com.system.backend.manage.building.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.backend.manage.building.dto.AuthTokenDTO;
import com.system.backend.manage.building.security.GenerateToken;


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

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Primer Paso");
	
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


}
