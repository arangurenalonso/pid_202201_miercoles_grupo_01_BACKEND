package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Servicio;

public interface IServicioRepository extends JpaRepository<Servicio, Long> {
	Servicio findByNombre(String nombre);
}
