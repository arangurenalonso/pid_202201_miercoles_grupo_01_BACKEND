package com.system.backend.manage.building.dto.salida;

import com.system.backend.manage.building.entity.Departamento;

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
public class DepartamentoDTOSalida {

	private Long id;

	private String depnumero;

	private String deptelef;

	private int piso;

	public DepartamentoDTOSalida(Departamento dep) {
		this.id=dep.getId();
		this.depnumero= dep.getDepnumero();
		this.deptelef= dep.getDeptelef();
		this.piso= dep.getPiso();
	}

}
