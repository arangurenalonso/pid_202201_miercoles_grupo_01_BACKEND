package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.VisitanteDTO;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Visitante;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IPersonaRepository;
import com.system.backend.manage.building.repository.IVisitanteRepository;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.VisitanteService;

@Service
public class VisitanteServiceImpl implements VisitanteService{

	@Autowired
	private IVisitanteRepository visitanteRepositorio;
	@Autowired
	private IPersonaRepository personaRepo;
	@Autowired
	private PersonaService PersonaServ;
	
	@Override
	public Visitante BuscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transactional
	public Visitante crearVisitante(VisitanteDTO visitanteCreate) {
		Persona personaRegistro=personaRepo.findById(visitanteCreate.getIdPersonaRegistro()).orElseThrow(() -> new CustomAppException(
				"La persona con id '" + visitanteCreate.getIdPersonaRegistro() + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		
		PersonaServ.validateNewdni(visitanteCreate.getDni());
		
		Persona per = new Persona();
		per.setNombre(visitanteCreate.getNombre());
		per.setApellido(visitanteCreate.getApellido());
		per.setDni(visitanteCreate.getDni());
		per.setEstado(true);
		per.setCreateAt(new Date());
		per.setPersonaRegistro(personaRegistro);
		
		
		Persona personaCreada=personaRepo.save(per);
		
		Visitante visitante=new Visitante();
		visitante.setPersona(personaCreada);		
		Visitante visitanteCreado=visitanteRepositorio.save(visitante);		
		
		return visitanteCreado;//
	}
	@Override
	public List<Visitante> listarTodos() {
		List<Visitante> visitantes=visitanteRepositorio.findAll();
		return visitantes;
	}
	
	
	
	
	@Override
	public Visitante actualizar(VisitanteDTO visitanteUpdate, long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Visitante eliminar(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
