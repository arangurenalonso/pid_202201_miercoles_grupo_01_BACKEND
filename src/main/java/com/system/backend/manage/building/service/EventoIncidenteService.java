package com.system.backend.manage.building.service;


import com.system.backend.manage.building.dto.entrada.EventoIncidenteDTO;
import com.system.backend.manage.building.dto.salida.EventoIncidenteDTOSalida;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.EventoIncidente;

public interface EventoIncidenteService {
	
	public EventoIncidenteDTOSalida registrar(EventoIncidenteDTO eventoIncidenteDTO);

	public EventoIncidenteDTOSalida statusUpdate(EventoIncidenteDTO eventoIncidenteDTO);
	
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

	public EventoIncidente getById(long id);
	
}
