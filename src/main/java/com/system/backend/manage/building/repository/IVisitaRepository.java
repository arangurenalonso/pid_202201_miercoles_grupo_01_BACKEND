package com.system.backend.manage.building.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.Visita;

public interface IVisitaRepository extends JpaRepository<Visita, Long> {

//	//JPQL
//	//Query no con tablas sino con clases que tienen @Entity
	
	@Query("SELECT  v FROM"
			+ " Visita v "
			+ " JOIN v.visitante vis "
			+ " JOIN v.departamento dep "
			+ " JOIN v.propietario prop "
			+ " JOIN vis.persona visPer "
			+ " where "
//			+ " CONCAT(visPer.nombre,' ',visPer.apellido) LIKE CONCAT('%',:nombre,'%') AND "
//			+ " visPer.dni LIKE CONCAT('%',:dni,'%') and"
			+ " v.estado= :estado")
	public abstract Page<Visita> listarVisitasByNombreDniEstado( int estado,Pageable pageable);
	//public abstract Page<Visita> listarVisitasByNombreDniEstado( String nombre,String dni,int estado,Pageable pageable);

}
