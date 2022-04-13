package com.system.backend.manage.building.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomAppException extends RuntimeException {

	private String principalMessage;
	private int statusCode;
	private String exceptionMessage;
	private String specificExceptions;
	private HttpStatus estado;
	



	private static final long serialVersionUID = 1L;

}
