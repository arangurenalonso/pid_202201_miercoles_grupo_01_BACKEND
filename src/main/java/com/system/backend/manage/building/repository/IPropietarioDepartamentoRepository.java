package com.system.backend.manage.building.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.PropietarioDepartamento;


public interface IPropietarioDepartamentoRepository  extends JpaRepository<PropietarioDepartamento, Long>{
	@Query("SELECT  x FROM "
			+ "PropietarioDepartamento x"
			+ " JOIN x.propietario p "
			+ " on p.id = ?1")
	public abstract List<PropietarioDepartamento> buscarDepartamentosDelPropietario( long id_propietario);
}