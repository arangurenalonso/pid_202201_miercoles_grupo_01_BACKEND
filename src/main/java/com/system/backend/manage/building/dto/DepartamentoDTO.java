package com.system.backend.manage.building.dto;

import javax.persistence.Entity;

import com.system.backend.manage.building.entity.Departamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoDTO {
	
	private Long id;
	private String depnumero;
	private String deptelef;
	private Boolean estado;
	
	
	
}