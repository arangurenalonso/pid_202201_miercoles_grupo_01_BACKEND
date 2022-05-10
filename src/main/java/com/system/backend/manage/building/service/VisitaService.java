package com.system.backend.manage.building.service;

import com.system.backend.manage.building.dto.entrada.VisitaDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Visita;

public interface VisitaService {
	
	public Visita registrarVisita(VisitaDTO visita);
		
	public PaginacionRespuesta listadoFiltroPaginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sorDir,
			String filtroNombre, String filtroDNI, int estado);

}
