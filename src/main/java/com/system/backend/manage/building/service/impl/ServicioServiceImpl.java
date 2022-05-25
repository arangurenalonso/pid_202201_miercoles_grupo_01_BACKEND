package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.ServicioDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.ServicioDTOSalida;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Servicio;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IPersonaRepository;
import com.system.backend.manage.building.repository.IServicioRepository;
import com.system.backend.manage.building.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

	@Autowired
	private IServicioRepository servicioRepository;
	@Autowired
	private IPersonaRepository personaRepo;
	

	@Override
	@Transactional
	public Servicio registrar(ServicioDTO servicioDTO) {
		
		Persona personaRegistro=personaRepo.findById(servicioDTO.getIdPersonaRegistro()).orElseThrow(() -> new CustomAppException(
				"La persona con Id '" + servicioDTO.getIdPersonaRegistro() + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		
		validateNuevoNombre(servicioDTO.getNombre());		
		Servicio servicio =new Servicio();		
		servicio.setNombre(servicioDTO.getNombre());
		servicio.setCosto(servicioDTO.getCosto());
		servicio.setDescripcion(servicioDTO.getDescripcion());
		servicio.setCreateAt(new Date());
		servicio.setPersonaRegistro(personaRegistro);
		servicio.setIsActive(true);
		
		return servicioRepository.save(servicio);
	}
	
	@Override
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina,sort);
		Page<Servicio> page = servicioRepository.findAll(pageable);
		
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
	public ServicioDTOSalida BuscarPorID(long id) {
		Servicio servicio = buscarServicio(id);
		return new ServicioDTOSalida(servicio);
	}
	
	
	@Override
	@Transactional
	public Servicio actualizar(ServicioDTO servicioDTO) {
		Servicio servicio = buscarServicio(servicioDTO.getId());
		
		if(!servicio.getNombre().toUpperCase().equals(servicioDTO.getNombre().toUpperCase())) {
			validateNuevoNombre(servicioDTO.getNombre());
			servicio.setNombre(servicioDTO.getNombre());
		}			
		
		servicio.setCosto(servicioDTO.getCosto());
		servicio.setDescripcion(servicio.getDescripcion());
	
		return servicioRepository.save(servicio);
	}
	@Override
	public Servicio changeActive(long id) {
		Servicio servicio = buscarServicio(id);
		servicio.setIsActive(!servicio.getIsActive());
		
		return servicioRepository.save(servicio);
	}
	
	
	private Servicio buscarServicio(long id) {
		Servicio servicio = servicioRepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		return servicio;
	}
	private Servicio findByNombre(String nombre) {
		return servicioRepository.findByNombre(nombre.trim());
	}
	
	private boolean validateNuevoNombre(String nombre) {
		Servicio servicio = findByNombre(nombre);

		if (servicio != null) {
			throw new CustomAppException("El nombre de Servicio ya existe en la Base de datos", 409,
					UserImplConstant.NOMBRE_EXISTENTE(nombre) , "ServicioExistException",
					HttpStatus.CONFLICT);
		}
		return true;
	}

	@Override
	public List<Servicio> listadoAllServicio() {

		List<Servicio> servicios=servicioRepository.findAll();
		return servicios;
	}
	
}


	
	

	
	



