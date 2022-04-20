package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Familiar;


public interface IFamiliarRepository  extends JpaRepository<Familiar, Long>{
	
}
