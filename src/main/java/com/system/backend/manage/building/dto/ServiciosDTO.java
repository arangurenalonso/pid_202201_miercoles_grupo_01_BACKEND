package com.system.backend.manage.building.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ServiciosDTO {
	
	private Long id;
	private String codigo_serv;
	private String nombre_serv;
	private String Costo_serv;
	private String Descripcion_serv;
	private String NomEmpresa_servicio;

}
