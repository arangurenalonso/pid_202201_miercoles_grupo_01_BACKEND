package com.system.backend.manage.building.dto;

import java.util.List;
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
public class DepartamentoRespuesta {
	
	private List<DepartamentoDTO>contenido;
	private int numeroDePagina;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;
	public List<DepartamentoDTO> getContenido() {
		return contenido;
	}

	

}
