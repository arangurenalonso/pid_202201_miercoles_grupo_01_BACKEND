package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Incidente;

public interface IIncidenteRepository extends JpaRepository<Incidente, Long> {
	Incidente findByNombre(String nombre);
}
