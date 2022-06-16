package com.system.backend.manage.building.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.BoletaServicio;
import com.system.backend.manage.building.entity.Visita;


public interface IHistorialIncidenteServicioRepository  extends JpaRepository<BoletaServicio, Long>{
	
	@Query("SELECT pp FROM"
			+ " BoletaServicio pp "
			+ " JOIN pp.departamento dep "
			+ " JOIN pp.servicio vis "
			+ " JOIN pp.monthYear my"
			+ " where "
			+ " my.month=:month and "
			+ " my.year=:year and "
			+ " dep.id= :idDepartamento ")
	public abstract List<Visita> listarProgramacionPagosMonthYearDepartamento( int month,int year,long idDepartamento);
	
	@Query("SELECT pp FROM"
			+ " BoletaServicio pp "
			+ " JOIN pp.departamento dep "
			+ " JOIN pp.servicio vis "
			+ " where "
			+ " pp.estado=1 and "
			+ " dep.id= :idDepartamento ")
	public abstract List<BoletaServicio> listarPagosPorPagarPorDepartamento( long idDepartamento);
	
}
