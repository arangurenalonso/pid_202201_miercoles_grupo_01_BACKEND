package com.system.backend.manage.building.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.repository.IPropietarioRepository;
import com.system.backend.manage.building.service.PropietarioService;

@Service
@Transactional
public class PropietarioServiceImpl  implements PropietarioService {
	@Autowired
	private IPropietarioRepository propietarioRepo;
	



	@Override
	public Propietario savePropietario(Propietario pro) {
		// TODO Auto-generated method stub
		return propietarioRepo.save(pro);
	}



	@Override
	public List<Propietario> listaPropietarios() {
		// TODO Auto-generated method stub
		return propietarioRepo.findAll();
	}
	
	
	
	
}

