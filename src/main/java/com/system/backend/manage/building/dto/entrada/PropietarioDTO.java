package com.system.backend.manage.building.dto.entrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PropietarioDTO {

	private Long id;
	/******************************
	 * Atributos de Propietario
	 * ****************************/
	@NotNull(message = "Campo 'Fecha de Nacimeinto' es obligatorio")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthdayDate;
	
	@NotBlank(message = "Campo 'Número de Celular' es obligatorio")
    @Pattern(regexp = "^-?[0-9]{9}+$",message="Debe contener 9 cifras y deben ser numeros enteros")
	private String numeroCelular;
	
	/******************************
	 * Atributos de Persona
	 * ****************************/
	@NotBlank(message="Campo 'Nombre Propietario' es obligatorio")
	@Size(min=3,max=40,message="Campo 'Nombre' Debe tener min 3 y max 40 caracteres")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Nombre debe contener solo letras")
	private String nombre;
	
	@NotBlank(message="Campo 'Apellido Propietario' es obligatorio")
	@Size(min=3,max=40,message="Campo 'Apellido' Debe tener min 3 y max 40 caracteres")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Apellido debe contener solo letras")
	private String apellido;
	
	@Pattern(regexp = "^-?[0-9]{8}+$", message = "Campon DNI tiene 8 caracterectes numéricos")
	private String dni;	
	
	/******************************
	 * Atributos de Usuario
	 * ****************************/
	@NotBlank(message="Campo 'Email Propietario' es obligatorio")
	@Email(message = "Debe ingresar un correo válido")
	private String email;
	
	@Size(min=4, message = "La clave debe tener minimo 4 caracteres")
	private String password;
	
	private Long idPersonaRegistro;
	
	@NotEmpty(message = "Debe contener por lo menos 1 departamento")
	private List<DepartamentoIgnoreProperties> departamentos=new ArrayList<>();
}
