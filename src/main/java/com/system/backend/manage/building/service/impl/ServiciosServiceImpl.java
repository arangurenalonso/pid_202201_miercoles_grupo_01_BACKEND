package com.system.backend.manage.building.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.dto.ServiciosDTO;
import com.system.backend.manage.building.entity.Servicios;
import com.system.backend.manage.building.excepciones.ServiciosNotFoundException;
import com.system.backend.manage.building.repository.IServicioRepository;
import com.system.backend.manage.building.service.ServicioService;


@Service
@Transactional
public class ServiciosServiceImpl implements ServicioService {
	
	@Autowired
	private IServicioRepository servRepo;
	
	//Convertir la entidad a DTO
	private ServiciosDTO mapearDTO(Servicios servicio) {
		ServiciosDTO servicioDTO = new ServiciosDTO();
		servicioDTO.setId(servicio.getId());
		servicioDTO.setCodigo_serv(servicio.getCodigo_serv());
		servicioDTO.setNombre_serv(servicio.getNombre_serv());
		servicioDTO.setDescripcion_serv(servicio.getDescripcion_serv());
		servicioDTO.setNomEmpresa_servicio(servicio.getNomEmpresa_servicio());
		servicioDTO.setCosto_serv(servicio.getCosto_serv());
		
		
		return servicioDTO;
	}
	
	//Convertir DTO a  entidad
	
	private Servicios mapearEntidad(ServiciosDTO servicioDTO){
		Servicios servicio = new Servicios();
		servicio.setCodigo_serv(servicioDTO.getCodigo_serv());
		servicio.setNombre_serv(servicioDTO.getNombre_serv());
		servicio.setDescripcion_serv(servicioDTO.getDescripcion_serv());
		servicio.setNomEmpresa_servicio(servicioDTO.getNomEmpresa_servicio());
		servicio.setCosto_serv(servicioDTO.getCosto_serv());
		
		servicio.setEstado(true);
		servicio.setCreateAt(new Date());
		return servicio;
	
	}

	@Override
	public ServiciosDTO crearServicio(ServiciosDTO servicioDTO) {
		
		Servicios servicio = mapearEntidad(servicioDTO);
		Servicios nuevoServicios = servRepo.save(servicio);
		
		ServiciosDTO servicioRespuesta = mapearDTO(nuevoServicios);
		
		
		
		return servicioRespuesta;
	}

	@Override
	public List<ServiciosDTO> obtenerTodosLosServicios(int numeroDePagina, int medidaDePagina) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina);
		Page<Servicios> servicios = servRepo.findAll(pageable);
		
		List<Servicios> listaServicios = servicios.getContent();
		return  listaServicios.stream().map(servicio -> mapearDTO(servicio)).collect(Collectors.toList());
	}

	@Override
	public ServiciosDTO obtenerServicioPorID(long id) {
		// TODO Auto-generated method stub
		Servicios servicio = servRepo.findById(id).orElseThrow(() -> new ServiciosNotFoundException("Servicios", "id", id));
		return mapearDTO(servicio);
	}

	@Override
	public ServiciosDTO actualizarServicio(ServiciosDTO servicioDTO, long id) {
		// TODO Auto-generated method stub
		Servicios servicio = servRepo.findById(id).orElseThrow(() -> new ServiciosNotFoundException("Servicios", "id", id));
		
		
		servicio.setNombre_serv(servicioDTO.getNombre_serv());
		servicio.setDescripcion_serv(servicioDTO.getDescripcion_serv());
		servicio.setNomEmpresa_servicio(servicioDTO.getNomEmpresa_servicio());
		servicio.setCosto_serv(servicioDTO.getCosto_serv());
		servicio.setEstado(true);
		
		
		Servicios servicioActualizado = servRepo.save(servicio);
		return mapearDTO(servicioActualizado);
	}

	@Override
	public ServiciosDTO eliminarServicio(long id) {
		// TODO Auto-generated method stub
		
		Servicios servicio = servRepo.findById(id).orElseThrow();
		servicio.setEstado(false);
		Servicios servicioActualizado = servRepo.save(servicio);
		return mapearDTO(servicioActualizado);
		
	}
	

}
