package com.system.backend.manage.building.jsonignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system.backend.manage.building.entity.Visita;

@JsonIgnoreProperties({"propietarioDepartamentos","personaRegistro"})
public class VisitaIgnoreProperties extends Visita{

	
	
}
