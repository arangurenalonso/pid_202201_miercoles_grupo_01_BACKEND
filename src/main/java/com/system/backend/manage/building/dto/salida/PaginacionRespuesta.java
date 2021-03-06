package com.system.backend.manage.building.dto.salida;


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
public class PaginacionRespuesta {
	
	private Object contenido;
	private int numeroDePagina;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private Boolean ultima;

	

}
