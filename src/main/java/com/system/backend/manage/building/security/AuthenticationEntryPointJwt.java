package com.system.backend.manage.building.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.repository.IUsuarioRepository;

@Component
public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint {

	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		String username = request.getParameter("username");
		Usuario usuario = usuarioRepo.findByUsername(username);
		
		String detail = usuario == null ? 
				"No existe el usuario " + username + " en la Base de datos"
				: "error al introducir la contraseña";
		//String password = request.getParameter("password");
		ResponseDetails errorDetalles = new ResponseDetails(HttpStatus.FORBIDDEN.value(), authException.getMessage(),
				detail);
		Response responseDTO = new Response();
		responseDTO.setType("Error");
		responseDTO.setDetalle(errorDetalles);
		responseDTO.setMessage("Error al momento de authenticarse; usuario y contraseña invalido");

		new ObjectMapper().writeValue(response.getOutputStream(), responseDTO);

	}

}