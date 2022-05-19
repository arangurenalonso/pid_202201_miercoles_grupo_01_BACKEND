package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.entrada.ProgramacionPagosDTO;
import com.system.backend.manage.building.entity.Servicio;

public interface ProgramacionPagosService {

	public List<Servicio> listPersona(ProgramacionPagosDTO programacionPagosDTO);
	
	
}
