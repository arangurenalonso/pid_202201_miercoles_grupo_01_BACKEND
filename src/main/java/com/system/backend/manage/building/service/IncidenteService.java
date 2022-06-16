package com.system.backend.manage.building.service;

import com.system.backend.manage.building.entity.Incidente;

import java.util.List;

import com.system.backend.manage.building.dto.entrada.IncidenteDTO;
import com.system.backend.manage.building.dto.salida.IncidenteDTOSalida;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;


public interface IncidenteService {
	
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
	
	public IncidenteDTOSalida BuscarPorID(long id);
	
	public Incidente registrar(IncidenteDTO servicioDTO);
	
	public Incidente actualizar(IncidenteDTO servicioDTO);
	
	public Incidente changeActive(long id);

	public Incidente buscarIncidente(long id);

	public List<Incidente> getAll();
}
