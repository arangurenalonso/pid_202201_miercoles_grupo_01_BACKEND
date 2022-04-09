package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.entity.Persona;

public interface PersonaService {

	List<Persona> listPersona();
	Persona savePersona(Persona per);
}
