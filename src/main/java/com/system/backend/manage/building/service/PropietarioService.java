package com.system.backend.manage.building.service;


import com.system.backend.manage.building.dto.PropietarioDTO;
import com.system.backend.manage.building.dto.PropietarioRespuesta;


public interface PropietarioService {

	public PropietarioRespuesta listaPropietarios(int numeroDePagina, int medidaDePagina,String ordenarPor, String sortDir);
	
	public PropietarioDTO savePropietario(PropietarioDTO propietarioDTO);

	public PropietarioDTO obtenerPropietarioPorId(long id);
	
	public PropietarioDTO actualizarPropietario(PropietarioDTO propietarioDTO, long id);
	
	public PropietarioDTO eliminarPropietario(long id);
}
