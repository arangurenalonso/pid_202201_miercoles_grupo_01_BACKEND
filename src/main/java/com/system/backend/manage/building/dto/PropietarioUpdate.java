package com.system.backend.manage.building.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class PropietarioUpdate {

	private Long id;
	@NotNull(message = "Campo 'Fecha de Nacimeinto' es obligatorio")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthdayDate;
	
	@NotBlank(message = "Numero Celular Requerido")
    @Pattern(regexp = "^-?[0-9]{9}+$",message="Debe contener 9 numeros enteros")
	private String numeroCelular;
	
	@NotBlank(message="Campo 'Nombre Propietario' es obligatorio")
	@Size(min=1,max=40)
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Nombre debe contener solo letras")
	private String nombre;
	
	@NotBlank(message="Campo 'Apellido Propietario' es obligatorio")
	@Size(min=1,max=40,message="Ingresar apellido")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Apellido debe contener solo letras")
	private String apellido;
	
	@NotBlank(message="ingresar dni")
	@Pattern(regexp = "^-?[0-9]{8}+$",message="Campon DNI tiene 8 caracterectes numéricos")
	private String dni;	
}
