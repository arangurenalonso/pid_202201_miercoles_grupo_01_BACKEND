package com.system.backend.manage.building.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.EventoIncidente;
import com.system.backend.manage.building.entity.PagoServicio;


public interface IPagoServicioRepository  extends JpaRepository<PagoServicio, Long>{
	
	@Query("SELECT  PS FROM"
			+ " PagoServicio PS "
			+ " JOIN PS.departamento dep "
			+ " where "
			+ " dep.id= :depId ")
	public abstract Page<PagoServicio> filtroByDepartamento( long depId,Pageable pageable);
}
