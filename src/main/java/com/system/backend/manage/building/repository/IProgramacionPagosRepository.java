package com.system.backend.manage.building.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.ProgramacionPagos;
import com.system.backend.manage.building.entity.Visita;


public interface IProgramacionPagosRepository  extends JpaRepository<ProgramacionPagos, Long>{
	
	@Query("SELECT pp FROM"
			+ " ProgramacionPagos pp "
			+ " JOIN pp.departamento dep "
			+ " JOIN pp.servicio vis "
			+ " where "
			+ " pp.month=:month and "
			+ " pp.year=:year and "
			+ " dep.id= :idDepartamento ")
	public abstract List<Visita> listarProgramacionPagosMonthYearDepartamento( int month,int year,long idDepartamento);
	
	
}
