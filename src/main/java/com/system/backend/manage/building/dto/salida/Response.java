package com.system.backend.manage.building.dto.salida;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString

public class Response {
	private String type;
	private String reason;
	private Object detalle;
	
}
