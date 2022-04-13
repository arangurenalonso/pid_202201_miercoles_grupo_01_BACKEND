package com.system.backend.manage.building.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

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
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;

@Component
public class AccessDeniedHandlerJwt implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		ResponseDetails httpResposneDetails=new ResponseDetails();
		httpResposneDetails.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
		httpResposneDetails.setMensaje(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		httpResposneDetails.setData(HttpStatus.UNAUTHORIZED);
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
