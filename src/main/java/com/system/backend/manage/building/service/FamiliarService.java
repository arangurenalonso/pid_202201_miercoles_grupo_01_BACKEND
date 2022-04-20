package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.MascotaCreateDTO;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Propietario;

public interface FamiliarService {
	public Familiar BuscarPorID(long id) ;
	
	public Familiar crearFamiliar(FamiliarCreateDTO familiarCreate, Long idPropietario);
	
	public List<Familiar> listarTodos() ;	

	public Familiar actualizar(FamiliarCreateDTO familiarUpdate, long id);
	
	public Familiar eliminar(long id);
}
