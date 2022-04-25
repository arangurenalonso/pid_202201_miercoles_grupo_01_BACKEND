package com.system.backend.manage.building.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


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
public class VisitanteDTO {
	private Long id;
	/******************************
	 * Atributos de Persona
	 * ****************************/
	 
	@NotBlank(message="Ingresar nombre")
	@Size(min=1,max=40)
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Nombre debe contener solo letras")
	private String nombre;
		@NotBlank(message="Ingresar Apellido")
		@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Nombre debe contener solo letras")
	private String apellido;
		@NotBlank(message="ingresar dni")
		@Pattern(regexp = "^-?[0-9]{9}+$",message="Debe contener 9 numeros enteros")
	private String dni;	
	
	private Long idPersonaRegistro;
}
