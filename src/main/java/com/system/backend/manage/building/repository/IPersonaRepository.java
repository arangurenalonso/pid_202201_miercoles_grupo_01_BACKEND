package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Persona;


public interface IPersonaRepository  extends JpaRepository<Persona, Long>{
	
}
