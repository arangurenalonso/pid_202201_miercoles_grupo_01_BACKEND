package com.system.backend.manage.building.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.system.backend.manage.building.entity.EventoIncidente;


public interface IEventoIncidenteRepository  extends JpaRepository<EventoIncidente, Long>{
	
	
	
}
