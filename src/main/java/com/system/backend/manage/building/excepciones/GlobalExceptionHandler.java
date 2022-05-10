package com.system.backend.manage.building.excepciones;

import java.util.HashMap;
import java.util.Map;


import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.system.backend.manage.building.dto.salida.Response;
import com.system.backend.manage.building.dto.salida.ResponseDetails;

/******************************************************************
 * Maneja todas las captura de excepción de toda nuestra aplicacion
 *****************************************************************/

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(CustomAppException.class)
	public ResponseEntity<?> manejarBlogAppException(CustomAppException e, WebRequest webRequest) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entro al manejarBlogAppException");
		ResponseDetails responseDetaile = new ResponseDetails();
		responseDetaile.setHttpStatusCode(e.getStatusCode());
		responseDetaile.setMensaje(e.getExceptionMessage());
		Map<String, String> errores = new HashMap<>();
		errores.put( e.getSpecificExceptions(),e.getPrincipalMessage()+" - "+e.getExceptionMessage());
		responseDetaile.setData(errores);

		Response response = new Response();
		response.setType("Error");
		response.setReason(e.getPrincipalMessage());
		response.setDetalle(responseDetaile);

		return new ResponseEntity<Response>(response, e.getEstado());
	}
	

	/************************************************************************************************
	 * Exepción que maneja problemas en la Base de datos
	 * *********************************************************************************************/
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<?> handleConflict(DataIntegrityViolationException ex, WebRequest webRequest) {

		
		ResponseDetails responseDetaile = new ResponseDetails();
		responseDetaile.setHttpStatusCode(500);//HttpStatus.INTERNAL_SERVER_ERROR
		responseDetaile.setMensaje("Conflicto con las restriccciones");
		Map<String, String> errores = new HashMap<>();
		errores.put( ex.getMessage(), ex.getMostSpecificCause().getMessage());
		responseDetaile.setData(errores);		
		
		Response response = new Response();
		response.setType("Error");
		response.setDetalle(responseDetaile);
		response.setReason(
				"la información no cumple las reglas establecidas en la tabla de la BD");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ResponseDetails> manejarDataAccessException(DataAccessException e, WebRequest webRequest) {
		ResponseDetails errorDetalles = new ResponseDetails(400000000, e.getMessage() + ":" + e.getMostSpecificCause(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/************************************************************************************************
	 * 
	 * *********************************************************************************************/
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> manejarBadCredentialsException(BadCredentialsException exception, WebRequest webRequest) {
		ResponseDetails responseDetails = new ResponseDetails(401,
				exception.getMessage() + "----" + exception.toString(), webRequest.getDescription(false));
		Response response = new Response();
		response.setType("Error");
		response.setDetalle(responseDetails);
		response.setReason("Acceso denegado; el usuario o contraseña son incorrectos");
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> manejarUsernameNotFoundException(UsernameNotFoundException exception, WebRequest webRequest) {
		ResponseDetails responseDetails = new ResponseDetails(2000000000, exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(responseDetails, HttpStatus.NOT_FOUND);
	}

	/************************************************************************************************
	 * 
	 * *********************************************************************************************/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> manejarGlobalException(Exception exception, WebRequest webRequest) {
		ResponseDetails errorDetalles = new ResponseDetails(600, exception+ "----" + exception.toString(),
				webRequest.getDescription(false));
		Response response = new Response();
		response.setType("Error");
		response.setDetalle(errorDetalles);
		response.setReason(
				">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>GlobalExceptionHandler Manejador de execpcion generalizada---------"+exception);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}


	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> manejarNullPointerException(NullPointerException e, WebRequest webRequest) {
		ResponseDetails errorDetalles = new ResponseDetails(600, e + "----" + e.toString(),
				webRequest.getDescription(false));
		Response response = new Response();
		response.setType("Error");
		response.setDetalle(errorDetalles);
		response.setReason(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>GlobalExceptionHandler Null pointer exception");

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> manejarRuntimeException(RuntimeException e, WebRequest webRequest) {
		ResponseDetails errorDetalles = new ResponseDetails(600, e + "----" + e.toString(),
				webRequest.getDescription(false));
		Response response = new Response();
		response.setType("Error");
		response.setDetalle(errorDetalles);
		response.setReason(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RuntimeException GLOBAL EXCEPTION");

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	
	/************************************************************************************************
	 * Exepción que maneja problemas en la validaciòn de formularios de ingreso
	 * *********************************************************************************************/	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String nombreCampo = ((FieldError) error).getField();
			String mensaje = error.getDefaultMessage();
			errores.put(nombreCampo, mensaje);
		});

		ResponseDetails detalle = new ResponseDetails(400, "Campos de entrada incorrectos", errores);
		Response response = new Response("Error", "Validation failed", detalle);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
