package com.system.backend.manage.building.dto;


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
public class DepartamentoDTO {
	
	private Long id;
	private String depnumero;
	private String deptelef;
	private Boolean estado;
	
	
	private Long idPersonaRegistro;
}