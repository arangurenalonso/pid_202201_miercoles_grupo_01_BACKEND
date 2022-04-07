package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Role;


public interface IRoleRepository  extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
