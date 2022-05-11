package com.system.backend.manage.building.dto.salida;


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
public class PropietarioDTOSalida {
	
	private Long id;
	private PersonaDTOSalida persona;
	private String numeroCelular;
	public PropietarioDTOSalida(Propietario propietario) {
		super();
		this.id = propietario.getId();
		this.numeroCelular=propietario.getNumeroCelular();
		this.persona =new PersonaDTOSalida( propietario.getPersona());
	}
}
