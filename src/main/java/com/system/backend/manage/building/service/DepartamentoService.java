package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.DepartamentoDTO;

public interface DepartamentoService {

	
	public DepartamentoDTO crearDepartamento (DepartamentoDTO departamentoDTO);
	

	public List<DepartamentoDTO> obtenertodoslosDepartamento();
	
	
	public DepartamentoDTO obtenerDepartamentosPorId(long id);
}
