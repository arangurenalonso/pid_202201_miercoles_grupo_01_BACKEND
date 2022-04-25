package com.system.backend.manage.building.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;

import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IPersonaRepository;
import com.system.backend.manage.building.service.PersonaService;

@Service
@Transactional
public class PersonaServiceImpl  implements PersonaService {
	@Autowired
	private IPersonaRepository personaRepo;
	


	@Override
	public List<Persona> listPersona() {
		// TODO Auto-generated method stub
		return personaRepo.listPersonaUsuario();
	}



	@Override
	public Persona savePersona(Persona per) {
		// TODO Auto-generated method stub
		return personaRepo.save(per);
	}

	@Override
	public boolean validateNewdni(String newdni) {
		Persona PersonaBydni = personaRepo.findBydni(newdni);

		if (PersonaBydni != null) {
			throw new CustomAppException("El DNI ya existe en la Base de datos", 401,
					UserImplConstant.DNI_EXISTENTE + " - " + newdni, "DNIExistException",
					HttpStatus.CONFLICT);
		}
		return true;
	}


	
	
	
}

