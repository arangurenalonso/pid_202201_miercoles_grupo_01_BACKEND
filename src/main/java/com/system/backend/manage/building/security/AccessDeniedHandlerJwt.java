package com.system.backend.manage.building.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.backend.manage.building.constant.SecurityConstant;
import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;

@Component
public class AccessDeniedHandlerJwt implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		ResponseDetails httpResposneDetails=new ResponseDetails();
		httpResposneDetails.setHttpStatusCode(HttpStatus.FORBIDDEN.value());
		httpResposneDetails.setMensaje(HttpStatus.FORBIDDEN.getReasonPhrase());
		httpResposneDetails.setData(HttpStatus.FORBIDDEN);
		Response httpResponse=new Response();
		httpResponse.setType("error");
		httpResponse.setReason(SecurityConstant.ACCESS_DENIED_MESSAGE);
		httpResponse.setDetalle(httpResposneDetails);
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream,httpResponse);
		outputStream.flush();
		
	}
}
/*
 * ResponseDetails responseDetails = new ResponseDetails(401,
				exception.getMessage() + "----" + exception.toString(), webRequest.getDescription(false));
		Response response = new Response();
		response.setType("Error");
		response.setDetalle(responseDetails);
		response.setReason("Acceso denegado; se requiere de los permisos necesario para acceder a este recurso");
 * */