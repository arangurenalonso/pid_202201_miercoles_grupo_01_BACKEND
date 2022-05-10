package com.system.backend.manage.building.dto.entrada;

import java.util.Date;

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
public class VisitaDTO {
	private Long id;
	
	private Date fechaHoraLlegada;

	private Date fechaHoraSalida;

	private String motivoVisita;
	
	private String observacionSalida;

	private Long propietario_id;
	
	private Long departamento_id;
	
	private Long visitante_id;
	
	private Long idPersonaRegistro;
}
