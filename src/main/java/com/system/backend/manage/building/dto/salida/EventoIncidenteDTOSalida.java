package com.system.backend.manage.building.dto.salida;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.EventoIncidente;
import com.system.backend.manage.building.entity.HistorialIncidentes;
import com.system.backend.manage.building.entity.Persona;

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
public class EventoIncidenteDTOSalida {

	private Long id;
	
	private DepartamentoDTOSalida departamento;

	private IncidenteDTOSalida incidente;//1: Registrado; 2:atendido
	
	private int estado;
	
    private List<HistorialIncidentesDTOSalida> historialIncidentes;

	public EventoIncidenteDTOSalida(EventoIncidente obj) {
		this.id=obj.getId();
		this.departamento= new DepartamentoDTOSalida(obj.getDepartamento());
		this.incidente=new IncidenteDTOSalida(obj.getIncidente());
		this.estado=obj.getEstado();
		this.historialIncidentes= obj.getHistorialIncidentes().stream().map(HI->{
																		return new HistorialIncidentesDTOSalida(HI);
																					}).collect(Collectors.toList());
	}

}
