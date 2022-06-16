package com.system.backend.manage.building.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.EventoIncidente;
import com.system.backend.manage.building.entity.Visita;


public interface IEventoIncidenteRepository  extends JpaRepository<EventoIncidente, Long>{
	
	
	@Query("SELECT  EI FROM"
			+ " EventoIncidente EI "
			+ " JOIN EI.departamento dep "
			+ " where "
			+ " dep.id= :depId ")
	public abstract Page<EventoIncidente> filtroByDepartamento( long depId,Pageable pageable);
	
	@Query("SELECT  EI FROM"
			+ " EventoIncidente EI "
			+ " JOIN EI.incidente inci "
			+ " where "
			+ " inci.id= :inciID ")
	public abstract Page<EventoIncidente> filtroByIncidente( long inciID,Pageable pageable);
}
