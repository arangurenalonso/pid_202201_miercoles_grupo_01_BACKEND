package com.system.backend.manage.building.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProgramacionPagosServicio {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idpropagoservicio" )
	private Long id;
	
	@Column(nullable = false, name = "cod_propagoservicio" )
	private String cod_propagoservicio;
	

}
