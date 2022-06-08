package com.system.backend.manage.building.service;

import com.system.backend.manage.building.dto.entrada.CancelarPagosDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.PagoServicio;

public interface PagoServicioService {

	
	public PagoServicio cancelarServicios(CancelarPagosDTO cancelarPagosDTO);

	PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
	
}
