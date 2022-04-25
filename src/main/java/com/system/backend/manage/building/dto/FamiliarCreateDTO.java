package com.system.backend.manage.building.dto;



import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    
	@NotBlank
	@Size(min=1,max=40,message="ingresar nombre")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Nombre debe contener solo letras")
	private String nombre;
    
	@NotBlank
	@Size(min=1,max=40,message="ingresar apellido")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Apellido debe contener solo letras")
	private String apellido;
	@NotBlank(message="ingresar dni")
	@Pattern(regexp = "^-?[0-9]{9}+$",message="Debe contener 9 numeros enteros")
	private String dni;	
	
	private Long idPersonaRegistro;
}
