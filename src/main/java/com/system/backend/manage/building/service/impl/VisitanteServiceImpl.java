package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.VisitanteDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
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
	public VisitanteDTO BuscarPorID(long id) {
		Visitante visitante=visitanteRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"El visitante con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		VisitanteDTO vis=new VisitanteDTO();
		vis.setId(visitante.getId());
		vis.setDni(visitante.getPersona().getDni());
		vis.setApellido(visitante.getPersona().getApellido());
		vis.setNombre(visitante.getPersona().getNombre());
		return vis;
	}
	
	@Override
	public Visitante BuscarVisitante(long id) {
		Visitante visitante=visitanteRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"El visitante con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		return visitante;
	}
	
	@Override
	public Visitante actualizar(VisitanteDTO visitanteUpdate, long id) {
		// TODO Auto-generated method stub
	
		Visitante visitante=visitanteRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"El visitante con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));

		visitante.getPersona().setNombre(visitanteUpdate.getNombre());
		visitante.getPersona().setApellido(visitanteUpdate.getApellido());
		visitante.getPersona().setDni(visitanteUpdate.getDni());
		Visitante obj=visitanteRepositorio.save(visitante);
		return obj;

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
	public Visitante eliminar(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Visitante changeActive(long id) {
		Visitante visitante=visitanteRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"Visitante con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		visitante.getPersona().setEstado(!visitante.getPersona().getEstado());
		
		return visitanteRepositorio.save(visitante);
	}
	@Override
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir,
			String filtro, String filtroBy) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina,sort);
		
		Page<Visitante> visitante;
		switch (filtroBy) {
		  case "nombreyapellido":
			  visitante = visitanteRepositorio.buscarVisitanteByNombe(filtro,pageable);
		    break;
		  case "dni":
			  visitante = visitanteRepositorio.buscarVisitanteByDNI(filtro,pageable);
		    break;
		  default:
			  visitante = visitanteRepositorio.buscarVisitanteByNombe("",pageable);
		} 

		PaginacionRespuesta paginacion= new PaginacionRespuesta();
		paginacion.setContenido(visitante.getContent());
		paginacion.setNumeroDePagina(visitante.getNumber());
		paginacion.setMedidaPagina(visitante.getSize());
		paginacion.setTotalElementos(visitante.getTotalElements());
		paginacion.setTotalPaginas(visitante.getTotalPages());
		paginacion.setUltima(visitante.isLast());
		return paginacion;
	}

	
	
}
