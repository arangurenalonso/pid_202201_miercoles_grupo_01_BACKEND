package com.system.backend.manage.building.dto.salida;

import java.util.Date;

import com.system.backend.manage.building.entity.Incidente;

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
public class IncidenteDTOSalida {

	private Long id;

	private String nombre;
	
	private String descripcion;
	
	private Boolean isActive;

	private Date createAt;

	private PersonaDTOSalida personaRegistro;

	public IncidenteDTOSalida(Incidente incidente) {
		super();
		this.id = incidente.getId();
		this.nombre = incidente.getNombre();
		this.descripcion = incidente.getDescripcion();
		this.isActive = incidente.getIsActive();
		this.createAt = incidente.getCreateAt();
		this.personaRegistro = new PersonaDTOSalida(incidente.getPersonaRegistro());
		
	}


}
