package com.system.backend.manage.building.service;


import com.system.backend.manage.building.dto.PropietarioCreate;
import com.system.backend.manage.building.dto.PropietarioRespuesta;
import com.system.backend.manage.building.dto.PropietarioUpdate;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Propietario;


public interface PropietarioService {

	public PropietarioRespuesta listaPropietarios(int numeroDePagina, int medidaDePagina,String ordenarPor, String sortDir);
	
	public Propietario savePropietario(PropietarioCreate propietarioDTO);
	public Propietario savePropietario(Propietario propietario);
	public Propietario obtenerPropietarioPorId(long id);
	
	
	public Propietario eliminarPropietario(long id);

	public Propietario actualizarPropietario(PropietarioUpdate propietariUpdate, long id);
	
	public Propietario changeActive(long id);
}
