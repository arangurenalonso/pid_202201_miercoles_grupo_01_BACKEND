package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.entrada.ProgramacionPagosDTO;
import com.system.backend.manage.building.entity.ProgramacionPagos;
import com.system.backend.manage.building.entity.Servicio;

public interface ProgramacionPagosService {

	public List<ProgramacionPagos> registrarPagos(ProgramacionPagosDTO programacionPagosDTO);
	
	public List<Servicio> listaServiciosPendientePagosPorMonthYearDepartamento(ProgramacionPagosDTO programacionPagosDTO );
	
	
}
