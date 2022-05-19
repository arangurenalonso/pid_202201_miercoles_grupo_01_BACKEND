package com.system.backend.manage.building.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.IncidenteDTO;
import com.system.backend.manage.building.dto.salida.IncidenteDTOSalida;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Incidente;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IIncidenteRepository;
import com.system.backend.manage.building.repository.IPersonaRepository;
import com.system.backend.manage.building.service.IncidenteService;

@Service
public class IncidenteServiceImpl implements IncidenteService {

	@Autowired
	private IIncidenteRepository incidenteRepository;
	@Autowired
	private IPersonaRepository personaRepo;
	

	@Override
	@Transactional
	public Incidente registrar(IncidenteDTO incidenteDTO) {
		
		Persona personaRegistro=personaRepo.findById(incidenteDTO.getIdPersonaRegistro()).orElseThrow(() -> new CustomAppException(
				"La persona con Id '" + incidenteDTO.getIdPersonaRegistro() + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		
		validateNuevoNombre(incidenteDTO.getNombre());		
		Incidente incidente =new Incidente();		
		incidente.setNombre(incidenteDTO.getNombre());
		incidente.setDescripcion(incidenteDTO.getDescripcion());
		incidente.setCreateAt(new Date());
		incidente.setPersonaRegistro(personaRegistro);
		incidente.setIsActive(true);
		
		return incidenteRepository.save(incidente);
	}
	
	@Override
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina,sort);
		Page<Incidente> page = incidenteRepository.findAll(pageable);
		
		PaginacionRespuesta paginacion= new PaginacionRespuesta();
		paginacion.setContenido(page.getContent());
		paginacion.setNumeroDePagina(page.getNumber());
		paginacion.setMedidaPagina(page.getSize());
		paginacion.setTotalElementos(page.getTotalElements());
		paginacion.setTotalPaginas(page.getTotalPages());
		paginacion.setUltima(page.isLast());
		return paginacion;
	}
	@Override
	public IncidenteDTOSalida BuscarPorID(long id) {
		Incidente servicio = buscarIncidente(id);
		return new IncidenteDTOSalida(servicio);
	}
	
	
	@Override
	@Transactional
	public Incidente actualizar(IncidenteDTO incidenteDTO) {
		Incidente incidente = buscarIncidente(incidenteDTO.getId());
		
		if(!incidente.getNombre().toUpperCase().equals(incidenteDTO.getNombre().toUpperCase())) {
			validateNuevoNombre(incidenteDTO.getNombre());
			incidente.setNombre(incidenteDTO.getNombre());
		}
		
		incidente.setDescripcion(incidenteDTO.getDescripcion());
	
		return incidenteRepository.save(incidente);
	}
	
	@Override
	public Incidente changeActive(long id) {
		Incidente incidente = buscarIncidente(id);
		incidente.setIsActive(!incidente.getIsActive());
		
		return incidenteRepository.save(incidente);
	}
	
	
	private Incidente buscarIncidente(long id) {
		Incidente incidente = incidenteRepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		return incidente;
	}
	private Incidente findByNombre(String nombre) {
		return incidenteRepository.findByNombre(nombre.trim());
	}
	
	private boolean validateNuevoNombre(String nombre) {
		Incidente incidente = findByNombre(nombre);

		if (incidente != null) {
			throw new CustomAppException("El nombre de Incidente ya existe en la Base de datos", 409,
					UserImplConstant.NOMBRE_EXISTENTE(nombre) , "IncidenteExistException",
					HttpStatus.CONFLICT);
		}
		return true;
	}
	
}


	
	

	
	



