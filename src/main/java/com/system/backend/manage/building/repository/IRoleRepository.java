package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Rol;


public interface IRoleRepository  extends JpaRepository<Rol, Long>{
	Rol findByNombre(String nombre);
}
