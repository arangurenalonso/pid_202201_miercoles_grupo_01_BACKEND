package com.system.backend.manage.building.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.jsonignore.DepartamentoIgnoreProperties;

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
public class PropietarioCreate {

	private Long id;
	/******************************
	 * Atributos de Propietario
	 * ****************************/
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthdayDate;
	private String numeroCelular;
	
	/******************************
	 * Atributos de Persona
	 * ****************************/
	@NotBlank(message="Ingresar nombre")
	@Size(min=1,max=40)
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Nombre debe contener solo letras")
	private String nombre;
	@NotBlank(message="Ingresar apellido")
	@Size(min=1,max=40,message="Ingresar apellido")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Apellido debe contener solo letras")
	private String apellido;
	
	@Pattern(regexp = "[0-9]{9}")
	private String dni;	
	
	/******************************
	 * Atributos de Usuario
	 * ****************************/
	@Email(message = "Debe ingresar un correo válido")
	private String email;
	@Size(min=4)
	private String password;
	
	private Long idPersonaRegistro;
	private List<DepartamentoIgnoreProperties> departamentos=new ArrayList<>();
}
