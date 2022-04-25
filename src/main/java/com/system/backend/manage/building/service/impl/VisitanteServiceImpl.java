package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.system.backend.manage.building.dto.VisitanteDTO;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Visitante;
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
		PersonaServ.validateNewdni(visitanteCreate.getDni());
		Persona per=new Persona(null, 
				visitanteCreate.getNombre(), 
				visitanteCreate.getApellido(), 
				visitanteCreate.getDni(), 
				true, 
				new Date());
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
