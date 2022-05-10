package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.entrada.VisitanteDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Visitante;

public interface VisitanteService {
	public VisitanteDTO BuscarPorID(long id) ;
	
	public Visitante crearVisitante(VisitanteDTO visitanteCreate);
	
	public List<Visitante> listarTodos() ;	

	public Visitante actualizar(VisitanteDTO visitanteUpdate, long id);
	
	public Visitante eliminar(long id);
	
	public Visitante changeActive(long id);

	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sorDir,
			String filtro, String filtroBy);

	public Visitante BuscarVisitante(long id);
	
	
}
