package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.backend.manage.building.entity.Usuario;


public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByEmail(String email);
}
