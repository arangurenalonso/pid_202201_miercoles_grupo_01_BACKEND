package com.system.backend.manage.building.service;

import com.system.backend.manage.building.dto.entrada.CancelarPagosDTO;
import com.system.backend.manage.building.entity.PagoServicio;

public interface PagoServicioService {

	
	public PagoServicio cancelarServicios(CancelarPagosDTO cancelarPagosDTO);
	
}
