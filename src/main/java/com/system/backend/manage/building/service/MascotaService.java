package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.MascotaDTO;
import com.system.backend.manage.building.entity.Mascota;

public interface MascotaService {
	public Mascota crearMascota(MascotaDTO mascota, Long idPropietario);

	public List<Mascota> listarMascotaAll() ;
	
	public Mascota obtenerMascotaPorId(long id) ;

	public Mascota actualizar(MascotaDTO mascota);
	
	public Mascota changeActive(long id);
}
