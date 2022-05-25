package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.VisitaDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.VisitaDTOSalida;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.Visita;
import com.system.backend.manage.building.entity.Visitante;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IVisitaRepository;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.service.VisitaService;
import com.system.backend.manage.building.service.VisitanteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VisitaServiceImpl implements VisitaService {

	@Autowired
	private VisitanteService visitanteService;
	@Autowired
	private PropietarioService propietarioService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private IVisitaRepository visitaRepo;

	@Override
	public Visita registrarVisita(VisitaDTO visita) {
		isVisitaInProgress(visita.getVisitante_id());
		
		Visitante visitante = visitanteService.BuscarVisitante(visita.getVisitante_id());
		Propietario propietario = propietarioService.obtenerPropietarioPorId(visita.getPropietario_id());
		Departamento departamento = departamentoService.obtenerDepartamentosPorId(visita.getDepartamento_id());
		Persona persona = personaService.BuscarPersonaId(visita.getIdPersonaRegistro());

		Visita vis = new Visita();
		vis.setVisitante(visitante);
		vis.setPropietario(propietario);
		vis.setDepartamento(departamento);
		vis.setPersonaRegistro(persona);
		vis.setCreateAt(new Date());
		vis.setEstado(2);
		vis.setMotivoVisita(visita.getMotivoVisita());
		vis.setFechaHoraLlegada(visita.getFechaHoraLlegada());

		Visita visitaCreada = visitaRepo.save(vis);

		return visitaCreada;
	}

	@Override
	public PaginacionRespuesta listadoFiltroPaginacion(int numeroDePagina, int medidaDePagina, String ordenarPor,
			String sortDir, String filtroNombre, String filtroDNI, int estado) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

		Page<Visita> visitaPage=visitaRepo.listarVisitasByNombreDniEstado( filtroNombre,filtroDNI,estado, pageable);
		
		List<VisitaDTOSalida> listaVisita=visitaPage.getContent()
				.stream().map(v->{ 
					return new VisitaDTOSalida(v);
				}).collect(Collectors.toList());
		
		
		PaginacionRespuesta paginacion = new PaginacionRespuesta();
		paginacion.setContenido(listaVisita);
		paginacion.setNumeroDePagina(visitaPage.getNumber());
		paginacion.setMedidaPagina(visitaPage.getSize());
		paginacion.setTotalElementos(visitaPage.getTotalElements());
		paginacion.setTotalPaginas(visitaPage.getTotalPages());
		paginacion.setUltima(visitaPage.isLast());
		return paginacion;
	}

	@Override
	public Visita finalizarVisita(VisitaDTO visitaDTO) {
		Visita visita=BuscarPorID(visitaDTO.getId());
		visita.setEstado(3);
		visita.setFechaHoraSalida(visitaDTO.getFechaHoraSalida());
		//visita.setFechaHoraSalida(new Date());
		visita.setObservacionSalida(visitaDTO.getObservacionSalida());
		Visita visActualizada=visitaRepo.save(visita);
		return visActualizada;
	}

	@Override
	public Visita BuscarPorID(long id) {
		Visita visita=visitaRepo.findById(id).orElseThrow(() -> new CustomAppException(
				"El id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		return visita;
	}
	
	@Override
	public VisitaDTOSalida BuscarVisita(long id) {
		Visita visita=BuscarPorID(id);
		VisitaDTOSalida vs=new VisitaDTOSalida(visita);		
		return vs;
	}
	
	private boolean isVisitaInProgress(long visitanteId) {
		List<Visita> visitasEnProgreso = visitaRepo.isVisitaInProgress(visitanteId);
		log.info("-------------AAA------------");
		log.info(visitasEnProgreso.size()+"");
		if (visitasEnProgreso.size()>0) {
			throw new CustomAppException("El Visitante se encuentra en progreso", 409,
					"El visitante debe finalizar su visita actual para que pueda ser registrado otra vez" , "VisitanteInProgressException",
					HttpStatus.CONFLICT);
		}
		return true;
	}
}
