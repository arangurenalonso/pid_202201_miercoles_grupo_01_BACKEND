package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.VisitanteDTO;
import com.system.backend.manage.building.entity.Visitante;

public interface VisitanteService {
	public Visitante BuscarPorID(long id) ;
	
	public Visitante crearVisitante(VisitanteDTO visitanteCreate);
	
	public List<Visitante> listarTodos() ;	

	public Visitante actualizar(VisitanteDTO visitanteUpdate, long id);
	
	public Visitante eliminar(long id);
	public Visitante changeActive(long id);
}
