package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.entrada.FamiliarCreateDTO;
import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Mascota;

public interface FamiliarService {
	public Familiar BuscarPorID(long id) ;
	
	public Familiar crearFamiliar(FamiliarCreateDTO familiarCreate, Long idPropietario);
	
	public List<Familiar> listarTodos() ;	

	public Familiar editarFamiliar(FamiliarCreateDTO familiarUpdate);
	
	public Familiar changeActive(long id);
	
}
