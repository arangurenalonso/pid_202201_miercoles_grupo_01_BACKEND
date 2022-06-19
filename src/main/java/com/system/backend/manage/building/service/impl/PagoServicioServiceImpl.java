package com.system.backend.manage.building.service.impl;

import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.CancelarPagosDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.dto.salida.PagoServicioDTOSalida;
import com.system.backend.manage.building.entity.PagoServicio;
import com.system.backend.manage.building.entity.PagoServicioDetalle;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.BoletaServicio;
import com.system.backend.manage.building.entity.EventoIncidente;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IPagoServicioDetalleRepository;
import com.system.backend.manage.building.repository.IPagoServicioRepository;
import com.system.backend.manage.building.repository.IBoletaServicioRepository;
import com.system.backend.manage.building.service.PagoServicioService;
import com.system.backend.manage.building.service.PersonaService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagoServicioServiceImpl implements PagoServicioService {

	@Autowired
	private IBoletaServicioRepository boletaServicioRepository;
	@Autowired
	private IPagoServicioRepository pagoServicioRepository;
	@Autowired
	private IPagoServicioDetalleRepository pagoServicioDetalleRepository;
	@Autowired
	private PersonaService personaServ;
	
	@Override
	public PaginacionRespuesta paginacion(int numeroDePagina, int medidaDePagina,String ordenarPor,String sortDir,long depId) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina,sort);
		
		Page<PagoServicio> page;
		if(depId>0){
			page = pagoServicioRepository.filtroByDepartamento(depId,pageable);
		}
		else {
			page = pagoServicioRepository.findAll(pageable);
		}
		
		
		
		PaginacionRespuesta paginacion= new PaginacionRespuesta();
		paginacion.setContenido(page.getContent().stream().map(obj->{
																	return new PagoServicioDTOSalida(obj);
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
	public PagoServicio cancelarServicios(CancelarPagosDTO cancelarPagosDTO) {
		Persona personaRegistro=personaServ.BuscarPersonaId(cancelarPagosDTO.getIdPersonaRegistro());
		List<BoletaServicio> listaBoletaServicioCancelar = new ArrayList<>();
		
		log.info("Servicios a cancelar");
		cancelarPagosDTO.getPagosACancelar().stream().forEach(pc -> {
			log.info(pc.getServicio().getNombre());
			BoletaServicio bs=buscarBoletaServicio(pc.getId());
			bs.setEstado(2);
			listaBoletaServicioCancelar.add(bs);
		});
		double montoTotal = listaBoletaServicioCancelar.stream().mapToDouble(x->x.getCosto()).sum();
		
		PagoServicio pagoHeader=new PagoServicio(null,montoTotal,new Date(),personaRegistro,cancelarPagosDTO.getDepartamento());
		PagoServicio pagoServicioCreado= pagoServicioRepository.save(pagoHeader);
		
		List<PagoServicioDetalle> listaDetallePagoServicio = new ArrayList<>();
		listaBoletaServicioCancelar.stream().forEach(x -> {
			PagoServicioDetalle detalle=new PagoServicioDetalle(null,pagoServicioCreado,x);
			listaDetallePagoServicio.add(detalle);
		});
		pagoServicioDetalleRepository.saveAll(listaDetallePagoServicio);
		boletaServicioRepository.saveAll(listaBoletaServicioCancelar);
		return pagoHeader;
	}
	
	public BoletaServicio buscarBoletaServicio(long id) {
		BoletaServicio bs=boletaServicioRepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));		
		return bs;
	}

}
