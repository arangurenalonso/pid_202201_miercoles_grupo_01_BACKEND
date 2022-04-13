package com.system.backend.manage.building.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetails {

	private int httpStatusCode;
	private String mensaje;
	private Object data;


}
