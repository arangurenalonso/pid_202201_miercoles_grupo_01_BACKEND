package com.system.backend.manage.building.excepciones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.system.backend.manage.building.dto.ResponseDetails;
import com.system.backend.manage.building.dto.Response;

/******************************************************************
 * Maneja todas las captura de excepción de toda nuestra aplicacion
 * *****************************************************************/
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	/*
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> manejarResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(400,exception.getCause()+"------"+exception.getMessage()+"----"+exception.toString(), webRequest.getDescription(false));
		Response response=new Response();
		response.setType("Error");
		response.setData(errorDetalles);
		response.setMessage("Busqueda Realizada no encontrada");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	*/
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<?> handleConflict(DataIntegrityViolationException  ex, WebRequest webRequest) {
		
		String   errorMessage = ex.getMostSpecificCause().getMessage();
		ResponseDetails errorDetalles = new ResponseDetails(500,errorMessage, webRequest.getDescription(false));
		Response response=new Response();
		response.setType("Error");
		response.setDetalle(errorDetalles);
		response.setMessage("Conflicto con las restriccciones; la información no cumple las reglas establecidas en la tabla de la BD");
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/*
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> manejarBadCredentialsException(Exception exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(401,exception.getMessage()+"----"+exception.toString(), webRequest.getDescription(false));
		Response response=new Response();
		response.setType("Error");
		response.setData(errorDetalles);
		response.setMessage("Acceso denegado; el usuario o contraseña son incorrectos");
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);		
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorDetalles> manejarUsernameNotFoundException(Exception exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(2000000000,exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.NOT_FOUND);
	}
	*/
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ResponseDetails> manejarDataAccessException(Exception exception,WebRequest webRequest){
		ResponseDetails errorDetalles = new ResponseDetails(400000000,exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/*
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> manejarAccessDeniedException(AccessDeniedException exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(401,exception.getMessage()+"----"+exception.toString(), webRequest.getDescription(false));
		Response response=new Response();
		response.setType("Error");
		response.setData(errorDetalles);
		response.setMessage("Busqueda Realizada no encontrada+");
		
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
		
		
	}
	*/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> manejarGlobalException(Exception exception,WebRequest webRequest){
		ResponseDetails errorDetalles = new ResponseDetails(600,exception.getMessage()+"----"+exception.toString(), webRequest.getDescription(false));
		Response response=new Response();
		response.setType("Error");
		response.setDetalle(errorDetalles);
		response.setMessage("Busqueda Realizada no encontrada+");
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String nombreCampo = ((FieldError)error).getField();
			String mensaje = error.getDefaultMessage();
			
			errores.put(nombreCampo, mensaje);
		});
		
		return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
	}
}
