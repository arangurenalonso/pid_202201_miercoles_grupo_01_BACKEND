package com.system.backend.manage.building.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.repository.IMascotaRepository;
import com.system.backend.manage.building.service.MascotaService;

@Service
public class MascotaServiceImpl implements MascotaService{
	@Autowired
	private IMascotaRepository mascotaRepo;
	@Override
	public Mascota crearMascota(Mascota mascota) {
				
		return mascotaRepo.save(mascota);
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
