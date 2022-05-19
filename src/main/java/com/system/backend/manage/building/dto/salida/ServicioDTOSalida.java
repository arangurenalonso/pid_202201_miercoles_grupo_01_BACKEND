package com.system.backend.manage.building.dto.salida;

import java.util.Date;

import com.system.backend.manage.building.entity.Servicio;

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
public class ServicioDTOSalida {

	private Long id;

	private String nombre;
	
	private String descripcion;
	
	private double costo;
	
	private Boolean isActive;

	private Date createAt;

	private PersonaDTOSalida personaRegistro;

	public ServicioDTOSalida(Servicio servicio) {
		super();
		this.id = servicio.getId();
		this.nombre = servicio.getNombre();
		this.descripcion = servicio.getDescripcion();
		this.costo = servicio.getCosto();
		this.isActive = servicio.getIsActive();
		this.createAt = servicio.getCreateAt();
		this.personaRegistro = new PersonaDTOSalida(servicio.getPersonaRegistro());
		
	}


}
