package com.system.backend.manage.building.service;


import java.util.List;

import com.system.backend.manage.building.dto.entrada.PropietarioDTO;
import com.system.backend.manage.building.dto.entrada.PropietarioUpdate;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.jsonignore.PropietarioIgnoreProperties;


public interface PropietarioService {

	public PaginacionRespuesta listaPropietarios(int numeroDePagina, int medidaDePagina,String ordenarPor, String sortDir);
	
	public Propietario savePropietario(PropietarioDTO propietarioDTO);
	public Propietario savePropietario(Propietario propietario);
	public Propietario obtenerPropietarioPorId(long id);
	
	
	public Propietario eliminarPropietario(long id);

	public Propietario actualizarPropietario(PropietarioUpdate propietariUpdate, long id);
	
	public Propietario changeActive(long id);

	public List<PropietarioIgnoreProperties> getAll();

	public  List<Departamento> buscarDepartamentoXPropietario(long id);
}
