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
public class MascotaCreateDTO {
    
	@NotBlank
	@Size(min=1,max=40,message="ingresar mascota")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Debe contener letras no numeros ni simbolos")
	private String tipoMascota;	
	@NotBlank
	@Size(min=1,max=40,message="Ingresar nombre")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Debe contener letras no numeros ni simbolos")
	private String nombre;	
	@NotBlank
	@Size(min=1,max=40,message="Raza de mascota")
	@Pattern(regexp = "[A-Za-záéíóúñ ]*",message="Debe contener letras no numeros ni simbolos")
	private String raza;
	
}
