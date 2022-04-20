package com.system.backend.manage.building.dto;



import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class FamiliarCreateDTO {
	private Long id;
	/******************************
	 * Atributos de Familiar
	 * ****************************/
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthdayDate;
	
	private String parentesco;
	
	/******************************
	 * Atributos de Persona
	 * ****************************/
	private String nombre;
	private String apellido;
	@Size(min=8,max=8,message = "El DNI debe tener un tamaño de 8 caracteres")
	private String dni;	
	
}
