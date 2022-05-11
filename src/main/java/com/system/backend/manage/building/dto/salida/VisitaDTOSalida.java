package com.system.backend.manage.building.dto.salida;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.Visita;

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
public class VisitaDTOSalida {
	private Long id;

	private Date fechaHoraLlegada;

	private Date fechaHoraSalida;

	private String motivoVisita;

	private String observacionSalida;

	private Date createAt;

	private int estado;// 1: Registrado ; 2: Terminado; 3: Cancelado

	@JsonIncludeProperties(value = { "id", "numeroCelular", "persona" })
	private PropietarioDTOSalida propietario;

	private DepartamentoDTOSalida departamento;

	private VisitanteDTOSalida visitante;

	private PersonaDTOSalida personaRegistro;

	public VisitaDTOSalida(Visita vis) {
		super();
		this.id = vis.getId();
		this.fechaHoraLlegada = vis.getFechaHoraLlegada();
		this.fechaHoraSalida = vis.getFechaHoraSalida();
		this.motivoVisita = vis.getMotivoVisita();
		this.observacionSalida = vis.getObservacionSalida();
		this.createAt = vis.getCreateAt();
		this.estado = vis.getEstado();
		this.propietario = new PropietarioDTOSalida(vis.getPropietario());
		this.departamento = new DepartamentoDTOSalida(vis.getDepartamento());
		this.visitante = new VisitanteDTOSalida(vis.getVisitante());
		this.personaRegistro = new PersonaDTOSalida(vis.getPersonaRegistro());
	}

}
