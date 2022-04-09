package com.system.backend.manage.building.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.Persona;


public interface IPersonaRepository  extends JpaRepository<Persona, Long>{
	@Query("SELECT p FROM Persona p"
			+ " JOIN p.usuario u")
	public abstract List<Persona> listPersonaUsuario();

}
