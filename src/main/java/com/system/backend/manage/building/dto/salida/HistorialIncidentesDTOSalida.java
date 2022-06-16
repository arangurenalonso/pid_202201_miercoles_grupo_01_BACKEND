package com.system.backend.manage.building.dto.salida;

import java.util.Date;

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
public class HistorialIncidentesDTOSalida {

	private Long id;
	
	private Date fecha;

	private int estado;//1: Registrado; 2:atendido
	
	private String comentario;
	
    private PersonaDTOSalida personaRegistro;

	public HistorialIncidentesDTOSalida(HistorialIncidentes obj) {
		this.id=obj.getId();
		this.fecha= obj.getFecha();
		this.estado= obj.getEstado();
		this.comentario= obj.getComentario();
		this.personaRegistro=new PersonaDTOSalida(obj.getPersonaRegistro());
	}

}
