package com.system.backend.manage.building.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Propietario;


public interface IPropietarioRepository  extends JpaRepository<Propietario, Long>{

	@Query("SELECT  d FROM "
			+ "Propietario p"
			+ " JOIN p.propietarioDepartamentos pd "
			+ " JOIN pd.departamento d "
			+ " on p.id=:id ")
	public abstract List<Departamento> buscarDepartamentoXPropietario( long id);
}
