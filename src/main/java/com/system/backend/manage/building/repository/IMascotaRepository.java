package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Mascota;

public interface IMascotaRepository extends JpaRepository<Mascota, Long> {
	Mascota findByNombre(String name);
}
