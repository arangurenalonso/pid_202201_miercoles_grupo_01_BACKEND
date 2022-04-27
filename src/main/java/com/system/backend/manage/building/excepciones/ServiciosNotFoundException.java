package com.system.backend.manage.building.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServiciosNotFoundException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	private String nombreDelRecurso;
	private String nombreDelCampo ;
	private long valorDelCampo;
	
	public ServiciosNotFoundException(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {
		super(String.format("%s No encontrado con : %s : '%s' ",nombreDelRecurso,nombreDelCampo,valorDelCampo));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCampo = valorDelCampo;
	}

}
