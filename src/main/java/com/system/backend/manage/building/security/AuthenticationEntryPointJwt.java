package com.system.backend.manage.building.security;

import java.io.IOException;
import java.io.OutputStream;

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
import com.system.backend.manage.building.constant.SecurityConstant;
import com.system.backend.manage.building.dto.Response;
import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.repository.IUsuarioRepository;

@Component
public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint {


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entramos a AuthenticationEntryPoint: ");
		System.out.println(">>>>>>>>>>>>>>>>> GAAAAAAAAAAAAAA::"+authException);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		ResponseDetails httpResposneDetails=new ResponseDetails();
		httpResposneDetails.setHttpStatusCode(HttpStatus.FORBIDDEN.value());
		httpResposneDetails.setMensaje(HttpStatus.FORBIDDEN.getReasonPhrase());
		httpResposneDetails.setData(HttpStatus.FORBIDDEN);
		Response httpResponse=new Response();
		httpResponse.setType("error");
		httpResponse.setReason(SecurityConstant.FORBIDDEN_MESSAGE);
		httpResponse.setDetalle(httpResposneDetails);
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream,httpResponse);
		outputStream.flush();

		
	
	}

}