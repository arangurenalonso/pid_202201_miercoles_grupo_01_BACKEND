package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.EventoIncidenteDTO;
import com.system.backend.manage.building.dto.salida.EventoIncidenteDTOSalida;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.BoletaServicio;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.EventoIncidente;
import com.system.backend.manage.building.entity.HistorialIncidentes;
import com.system.backend.manage.building.entity.Incidente;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IEventoIncidenteRepository;
import com.system.backend.manage.building.repository.IHistorialIncidentesRepository;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.EventoIncidenteService;
import com.system.backend.manage.building.service.IncidenteService;
import com.system.backend.manage.building.service.PersonaService;

@Service
public class EventoIncidenteServiceImpl implements EventoIncidenteService {

	@Autowired
	public PersonaService personaService;
	@Autowired
	public DepartamentoService departamentoService;
	@Autowired
	public IncidenteService incidenteService;
	@Autowired
	public IEventoIncidenteRepository eventoIncidenteRepository;	
	@Autowired
	public IHistorialIncidentesRepository historialIncidentesRepository;	
	
	@Override
	@Transactional
	public EventoIncidenteDTOSalida registrar(EventoIncidenteDTO eventoIncidenteDTO) {
		Persona personaRegistro=personaService.BuscarPersonaId(eventoIncidenteDTO.getIdPersonaRegistro());
		Departamento departamento=departamentoService.obtenerDepartamentosPorId(eventoIncidenteDTO.getDepartamento_id());
		Incidente incidente=incidenteService.buscarIncidente(eventoIncidenteDTO.getIncidente_id());
		
		EventoIncidente eventoIncidente=new EventoIncidente(null,departamento,incidente,1);
		EventoIncidente eventoIncidenteRegistrado=eventoIncidenteRepository.save(eventoIncidente);
		
		HistorialIncidentes historialIncidentes=new HistorialIncidentes(null, new Date(),1,eventoIncidenteDTO.getComentario(),eventoIncidenteRegistrado,personaRegistro);
		historialIncidentesRepository.save(historialIncidentes);
		
		return new EventoIncidenteDTOSalida(eventoIncidenteRegistrado);
	}

	@Override
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir,long depId, long inciID) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina,sort);
		Page<EventoIncidente> page;
		if(depId>0){
			page = eventoIncidenteRepository.filtroByDepartamento(depId,pageable);
		}
		else if(inciID>0){
			 page = eventoIncidenteRepository.filtroByIncidente(inciID,pageable);
		}
		else {
			page = eventoIncidenteRepository.findAll(pageable);
		}
		
		
		
		PaginacionRespuesta paginacion= new PaginacionRespuesta();
		paginacion.setContenido(page.getContent().stream().map(obj->{
																	return new EventoIncidenteDTOSalida(obj);
																	}).collect(Collectors.toList()));
		paginacion.setNumeroDePagina(page.getNumber());
		paginacion.setMedidaPagina(page.getSize());
		paginacion.setTotalElementos(page.getTotalElements());
		paginacion.setTotalPaginas(page.getTotalPages());
		paginacion.setUltima(page.isLast());
		return paginacion;

	}

	@Override
	@Transactional
	public EventoIncidenteDTOSalida statusUpdate(EventoIncidenteDTO eventoIncidenteDTO) {
		Persona personaRegistro=personaService.BuscarPersonaId(eventoIncidenteDTO.getIdPersonaRegistro());
		
		EventoIncidente evento =this.getById(eventoIncidenteDTO.getId());
		
		
		HistorialIncidentes historialIncidentes=new HistorialIncidentes(null, new Date(),eventoIncidenteDTO.getEstado(),eventoIncidenteDTO.getComentario(),evento,personaRegistro);
		historialIncidentesRepository.save(historialIncidentes);
		
		evento.setEstado(eventoIncidenteDTO.getEstado());
		EventoIncidente eventoActualizado=eventoIncidenteRepository.save(evento);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+evento.getEstado());
		System.out.println(eventoActualizado.getId()+" "+eventoActualizado.getEstado());
		
		return new EventoIncidenteDTOSalida(eventoActualizado);
	}

	@Override
	public EventoIncidente getById(long id) {
		EventoIncidente obj=eventoIncidenteRepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));		
		return obj;
	}
	
	
}


	
	

	
	



