package com.system.backend.manage.building.dto.salida;



import com.system.backend.manage.building.entity.Visitante;

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
public class VisitanteDTOSalida {
	private Long id;
	private PersonaDTOSalida persona;

	public VisitanteDTOSalida(Visitante visitante) {
		super();
		this.id = visitante.getId();
		this.persona =new PersonaDTOSalida( visitante.getPersona());
	}

}
