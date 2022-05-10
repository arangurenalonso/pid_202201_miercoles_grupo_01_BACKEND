package com.system.backend.manage.building.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.backend.manage.building.dto.entrada.AuthTokenDTO;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;
import com.system.backend.manage.building.service.UsuarioService;
import com.system.backend.manage.building.utils.GenerateToken;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private GenerateToken generateToken;

	private AuthenticationManager authenticationManager;

	private UsuarioService usuarioService;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager, GenerateToken generateToken,
			UsuarioService usuarioService) {
		this.authenticationManager = authenticationManager;
		this.generateToken = generateToken;
		this.usuarioService = usuarioService;
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
		responseToken.put("usuario", usuarioService.getUser(user.getUsername()));
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), responseToken);
	}

	// @Autowired
	// @Qualifier("handlerExceptionResolver")
	// private HandlerExceptionResolver resolver;

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Authenticacion fallida");
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.BAD_REQUEST.value());

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("username", username);
		responseData.put("password", password);
		
		ResponseDetails responseDetaile = new ResponseDetails();
		responseDetaile.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDetaile.setMensaje("Usuario y contraseña invalido; intente otra conbinación");
		responseDetaile.setData(responseData);

		Response resp = new Response();
		resp.setType("Error");
		resp.setReason("Error en el incio de sesión: su intento de inicio de sesión no fue exitoso por credenciales erroneas");
		resp.setDetalle(responseDetaile);

		new ObjectMapper().writeValue(response.getOutputStream(), resp);

		// resolver.resolveException(request, response, null, authException);

	}

}
