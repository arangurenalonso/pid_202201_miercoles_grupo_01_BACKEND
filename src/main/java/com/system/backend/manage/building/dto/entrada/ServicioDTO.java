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
public class ServicioDTO {
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private double costo;	
	
	private Long idPersonaRegistro;
}
