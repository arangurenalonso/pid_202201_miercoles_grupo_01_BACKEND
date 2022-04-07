package com.system.backend.manage.building.excepciones;

import org.springframework.http.HttpStatus;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomAppException extends RuntimeException {

	private String principalMessage;
	private int statusCode;
	private String exceptionMessage;
	private String specificExceptions;
	private HttpStatus estado;
	



	private static final long serialVersionUID = 1L;

}
