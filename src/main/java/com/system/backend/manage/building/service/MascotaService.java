package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.dto.MascotaCreateDTO;
import com.system.backend.manage.building.entity.Mascota;

public interface MascotaService {
	public Mascota crearMascota(MascotaCreateDTO mascota, Long idPropietario);

	public List<Mascota> listarMascotaAll() ;
	
	public Mascota obtenerMascotaPorId(long id) ;

	public Mascota actualizarMascota(Mascota mascota, long id);
	
	public Mascota eliminarMascota(long id);
}
