package com.system.backend.manage.building.dto;

import java.util.Date;

import com.system.backend.manage.building.entity.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropietarioDTO {

	private Long id;
	private Date birthdayDate;
	private String foto;
	private String numeroCelular;
	private Persona persona;
	private Boolean estado;

		
}
