package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.entity.Propietario;

public interface PropietarioService {

	List<Propietario> listaPropietarios();
	
	Propietario savePropietario(Propietario pro);
}
