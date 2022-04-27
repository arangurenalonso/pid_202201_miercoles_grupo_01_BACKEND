package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.ServiciosDTO;

public interface ServicioService {
	
	public ServiciosDTO crearServicio(ServiciosDTO servicioDTO);
	
	public List<ServiciosDTO> obtenerTodosLosServicios(int numeroDePagina, int medidaDePagina);

	
	public ServiciosDTO obtenerServicioPorID(long id);
	
	public ServiciosDTO actualizarServicio(ServiciosDTO servicioDTO,long id);
	
	public ServiciosDTO eliminarServicio(long id);

}
