package com.system.backend.manage.building.dto.entrada;



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
public class EventoIncidenteDTO {
    
	private Long id;
	
	private Long departamento_id;
	
	private Long incidente_id;
	
	private Long idPersonaRegistro;
	
	private String comentario;
	
	private int estado;
}
