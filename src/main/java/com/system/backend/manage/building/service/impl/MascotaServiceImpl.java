package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.dto.MascotaCreateDTO;
import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.repository.IMascotaRepository;
import com.system.backend.manage.building.service.MascotaService;
import com.system.backend.manage.building.service.PropietarioService;

@Service
public class MascotaServiceImpl implements MascotaService{
	@Autowired
	private IMascotaRepository mascotaRepo;
	@Autowired
	private PropietarioService propietarioService;
	
	@Override
	public Mascota crearMascota(MascotaCreateDTO mascota, Long idPropietario) {
		Propietario propietario=propietarioService.obtenerPropietarioPorId(idPropietario);
		Mascota mascotaNueva=new Mascota();
		mascotaNueva.setActive(true);		
		mascotaNueva.setCreateAt(new Date());
		mascotaNueva.setNombre(mascota.getNombre());
		mascotaNueva.setPropietario(propietario);
		mascotaNueva.setRaza(mascota.getRaza());
		mascotaNueva.setTipoMascota(mascota.getTipoMascota());
		
		
		return mascotaRepo.save(mascotaNueva);
	}
	
	

	@Override
	public List<Mascota> listarMascotaAll() {
		
		return mascotaRepo.findAll();
	}

	@Override
	public Mascota obtenerMascotaPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mascota actualizarMascota(Mascota mascota, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mascota eliminarMascota(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
