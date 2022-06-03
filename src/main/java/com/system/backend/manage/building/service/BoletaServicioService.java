package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.entrada.BoletaServicioDTO;
import com.system.backend.manage.building.dto.salida.BoletaServicioDTOSalida;
import com.system.backend.manage.building.entity.BoletaServicio;

public interface BoletaServicioService {

	public List<BoletaServicio> registrarPagos(BoletaServicioDTO programacionPagosDTO);
	
	public List<BoletaServicioDTOSalida> listarPagosPorPagarPorDepartamento(long  idDepartamento );
	
	
}
