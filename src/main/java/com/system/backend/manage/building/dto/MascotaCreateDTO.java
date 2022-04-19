package com.system.backend.manage.building.dto;


import com.system.backend.manage.building.entity.Propietario;

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

	private String tipoMascota;	
	
	private String nombre;	
	
	private String raza;
	
}
