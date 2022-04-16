package com.system.backend.manage.building.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.dto.PropietarioDTO;
import com.system.backend.manage.building.dto.PropietarioRespuesta;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.repository.IPropietarioRepository;
import com.system.backend.manage.building.service.PropietarioService;

@Service
public class PropietarioServiceImpl implements PropietarioService {

	@Autowired
	private IPropietarioRepository propietarioRepositorio;
	
	@Override
	public PropietarioDTO savePropietario(PropietarioDTO propietarioDTO) {
		 Propietario propietario = mapearEntidad(propietarioDTO);
		 
		 Propietario nuevoPropietario = propietarioRepositorio.save(propietario);
		 
		 PropietarioDTO propietarioRespuesta = mapearDTO(nuevoPropietario);
		 
		 return propietarioRespuesta;
	}

	@Override
	public PropietarioRespuesta listaPropietarios(int numeroDePagina, int medidaDePagina, String ordenarPor,String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
		
		Page<Propietario> propietarios = propietarioRepositorio.findAll(pageable);
		
		
		List<Propietario> listaDePropietarios = propietarios.getContent();
		List<PropietarioDTO> contenido = listaDePropietarios.stream().map(propietario -> mapearDTO(propietario)).collect(Collectors.toList());
		
		PropietarioRespuesta propietarioRespuesta = new PropietarioRespuesta();
		propietarioRespuesta.setContenido(contenido);
		propietarioRespuesta.setNumeroPagina(propietarios.getNumber());
		propietarioRespuesta.setMedidaPagina(propietarios.getSize());
		propietarioRespuesta.setTotalElementos(propietarios.getTotalElements());
		propietarioRespuesta.setTotalPaginas(propietarios.getTotalPages());
		propietarioRespuesta.setUltima(propietarios.isLast());
		
		return propietarioRespuesta;
	}

	private PropietarioDTO mapearDTO(Propietario propietario) {
		PropietarioDTO propietarioDTO = new PropietarioDTO();
		
		propietarioDTO.setId(propietario.getId());
		
		propietarioDTO.setBirthdayDate(propietario.getBirthdayDate());
		propietarioDTO.setFoto(propietario.getFoto());
		propietarioDTO.setNumeroCelular(propietario.getNumeroCelular());
		propietarioDTO.setPersona(propietario.getPersona());
		propietarioDTO.setEstado(propietario.getEstado());
		
		return propietarioDTO;
		
	}
	
	//Convertir DTO a Entidad
	private Propietario mapearEntidad(PropietarioDTO propietarioDTO) {
		
		Propietario propietario = new Propietario();
		
		propietario.setBirthdayDate(propietarioDTO.getBirthdayDate());
		propietario.setFoto(propietarioDTO.getFoto());
		propietario.setNumeroCelular(propietarioDTO.getNumeroCelular());
		propietario.setPersona(propietarioDTO.getPersona());
		propietario.setEstado(propietarioDTO.getEstado());
		return propietario;
		
	}

	@Override
	public PropietarioDTO obtenerPropietarioPorId(long id) {
		Propietario propietario = propietarioRepositorio.findById(id).orElseThrow();
		
		return mapearDTO(propietario);
	}

	@Override
	public PropietarioDTO actualizarPropietario(PropietarioDTO propietarioDTO, long id) {
		Propietario propietario = propietarioRepositorio.findById(id).orElseThrow();
		
		propietario.setBirthdayDate(propietarioDTO.getBirthdayDate());
		propietario.setFoto(propietarioDTO.getFoto());
		propietario.setNumeroCelular(propietarioDTO.getNumeroCelular());
		propietario.setPersona(propietarioDTO.getPersona());
		
		Propietario propietarioActualizado = propietarioRepositorio.save(propietario);
		
		return mapearDTO(propietarioActualizado);
	}

	@Override
	public PropietarioDTO eliminarPropietario(long id) {
		Propietario propietario = propietarioRepositorio.findById(id).orElseThrow();
		propietario.setEstado(false);
		Propietario propietarioActualizado = propietarioRepositorio.save(propietario);
		
		return mapearDTO(propietarioActualizado);
	}

	


	
}
