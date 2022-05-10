package com.system.backend.manage.building.jsonignore;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system.backend.manage.building.entity.Persona;

@JsonIgnoreProperties({"estado","create_at","usuario","personaRegistro"})
public class PersonaIgnoreProperties extends Persona{

	

	
	private static final long serialVersionUID = 1L;

	public PersonaIgnoreProperties(Persona persona) {
		super(persona.getId(),persona.getNombre(),persona.getApellido(),persona.getDni(),persona.getEstado(),persona.getCreateAt());
	}

}
