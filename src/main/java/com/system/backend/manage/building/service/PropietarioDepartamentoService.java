package com.system.backend.manage.building.service;


import java.util.List;

import com.system.backend.manage.building.dto.entrada.UpdatePropietarioDepartamentosDTO;
import com.system.backend.manage.building.entity.PropietarioDepartamento;


public interface PropietarioDepartamentoService {

	
	
	public List<PropietarioDepartamento> actualizarDepartamentosPropietario(long id_propietario,UpdatePropietarioDepartamentosDTO updatePropietarioDepartamentosDTO);
}
