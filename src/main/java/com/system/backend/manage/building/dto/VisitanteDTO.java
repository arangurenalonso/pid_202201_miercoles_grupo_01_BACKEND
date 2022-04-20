package com.system.backend.manage.building.dto;


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
	private String nombre;
	private String apellido;
	@Size(min=8,max=8,message = "El DNI debe tener un tamaño de 8 caracteres")
	private String dni;	
}
