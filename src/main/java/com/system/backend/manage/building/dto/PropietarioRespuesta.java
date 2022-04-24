package com.system.backend.manage.building.dto;

import java.util.List;

import com.system.backend.manage.building.entity.Propietario;

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
public class PropietarioRespuesta {

	private List<Propietario> contenido;
	private int numeroDePagina;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;
	
	
}
