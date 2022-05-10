package com.system.backend.manage.building.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.dto.entrada.VisitaDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.Visita;
import com.system.backend.manage.building.entity.Visitante;
import com.system.backend.manage.building.repository.IVisitaRepository;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.service.VisitaService;
import com.system.backend.manage.building.service.VisitanteService;

@Service
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
		vis.setEstado(1);
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

		Page<Visita> visitaPage=visitaRepo.listarVisitasByNombreDniEstado( estado, pageable);
		//Page<Visita> visitaPage=visitaRepo.listarVisitasByNombreDniEstado(filtroNombre, filtroDNI, estado, pageable);
		
		PaginacionRespuesta paginacion = new PaginacionRespuesta();
		paginacion.setContenido(visitaPage.getContent());
		paginacion.setNumeroDePagina(visitaPage.getNumber());
		paginacion.setMedidaPagina(visitaPage.getSize());
		paginacion.setTotalElementos(visitaPage.getTotalElements());
		paginacion.setTotalPaginas(visitaPage.getTotalPages());
		paginacion.setUltima(visitaPage.isLast());
		return paginacion;
	}

}
