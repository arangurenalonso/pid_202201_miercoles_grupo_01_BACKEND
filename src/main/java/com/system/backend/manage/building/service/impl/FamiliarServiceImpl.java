package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.FamiliarCreateDTO;

import com.system.backend.manage.building.entity.Familiar;
import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Propietario;

import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IFamiliarRepository;
import com.system.backend.manage.building.repository.IPersonaRepository;
import com.system.backend.manage.building.repository.IPropietarioRepository;
import com.system.backend.manage.building.service.FamiliarService;
import com.system.backend.manage.building.service.PersonaService;

@Service
public class FamiliarServiceImpl implements FamiliarService{
	@Autowired
	private IFamiliarRepository familiarRepositorio;
	@Autowired
	private IPropietarioRepository propietarioRepositorio;
	@Autowired
	private IPersonaRepository personaRepo;
	@Autowired
	private PersonaService PersonaServ;
	
	@Override
	public Familiar BuscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@Transactional
	public Familiar crearFamiliar(FamiliarCreateDTO familiarCreate,Long idPropietario) {
		PersonaServ.validateNewdni(familiarCreate.getDni());
		//validateNewdni(familiarCreate.getDni());
		Propietario propietario = propietarioRepositorio.findById(idPropietario).orElseThrow(() -> new CustomAppException(
				"El propietario con id '" + idPropietario + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		Persona personaRegistro=personaRepo.findById(familiarCreate.getIdPersonaRegistro()).orElseThrow(() -> new CustomAppException(
				"La persona con id '" + familiarCreate.getIdPersonaRegistro() + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		
		Persona per = new Persona();
		per.setNombre(familiarCreate.getNombre());
		per.setApellido(familiarCreate.getApellido());
		per.setDni(familiarCreate.getDni());
		per.setEstado(true);
		per.setCreateAt(new Date());
		per.setPersonaRegistro(personaRegistro);		
		Persona personaCreada=personaRepo.save(per);
		
		
		Familiar familiarNuevo=new Familiar();		
		familiarNuevo.setParentesco(familiarCreate.getParentesco());
		familiarNuevo.setBirthdayDate(familiarCreate.getBirthdayDate());
		familiarNuevo.setPersona(personaCreada);
		familiarNuevo.setPropietario(propietario);		
		Familiar familiarCreado=familiarRepositorio.save(familiarNuevo);
		
		
		return familiarCreado;//
	}


	@Override
	public Familiar editarFamiliar(FamiliarCreateDTO familiarUpdate) {
		Familiar familiar=familiarRepositorio.findById(familiarUpdate.getId()).orElseThrow(() -> new CustomAppException(
				"La familiar con id '" + familiarUpdate.getId() + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		
		familiar.setBirthdayDate(familiarUpdate.getBirthdayDate());
		familiar.setParentesco(familiarUpdate.getParentesco());
		familiar.getPersona().setNombre(familiarUpdate.getNombre());
		familiar.getPersona().setApellido(familiarUpdate.getApellido());
		familiar.getPersona().setDni(familiarUpdate.getDni());
		return familiarRepositorio.save(familiar);
	}

	@Override
	public List<Familiar> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Familiar changeActive(long id) {
		Familiar familiar=familiarRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"Familiar con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		familiar.getPersona().setEstado(!familiar.getPersona().getEstado());
		
		return familiarRepositorio.save(familiar);
	}



	
	


}
