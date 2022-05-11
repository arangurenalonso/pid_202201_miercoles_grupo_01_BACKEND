package com.system.backend.manage.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import com.system.backend.manage.building.entity.Visitante;
import org.springframework.data.domain.Pageable;


public interface IVisitanteRepository  extends JpaRepository<Visitante, Long>{


	@Query("SELECT  v FROM "
			+ "Visitante v"
			+ " JOIN v.persona p "
			+ " where CONCAT(p.nombre,' ',p.apellido) LIKE CONCAT('%',:nombre,'%') ")
	public abstract Page<Visitante> buscarVisitanteByNombe( String nombre,Pageable pageable);
	
	@Query("SELECT  v FROM "
			+ "Visitante v"
			+ " JOIN v.persona p "
			+ " where p.dni LIKE CONCAT('%',:dni,'%') ")
	public abstract Page<Visitante> buscarVisitanteByDNI( String dni,Pageable pageable);
}