package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.entity.Mascota;

public interface MascotaService {
	public Mascota crearMascota(Mascota mascota);

	public List<Mascota> listarMascotaAll() ;
	
	public Mascota obtenerMascotaPorId(long id) ;

	public Mascota actualizarMascota(Mascota mascota, long id);
	
	public Mascota eliminarMascota(long id);
}
