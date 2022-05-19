package com.system.backend.manage.building.service;

import com.system.backend.manage.building.dto.entrada.ServicioDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.ServicioDTOSalida;
import com.system.backend.manage.building.entity.Servicio;

public interface ServicioService {
	
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
	
	public ServicioDTOSalida BuscarPorID(long id);
	
	public Servicio registrar(ServicioDTO servicioDTO);
	
	public Servicio actualizar(ServicioDTO servicioDTO);
	
	public Servicio changeActive(long id);

}
