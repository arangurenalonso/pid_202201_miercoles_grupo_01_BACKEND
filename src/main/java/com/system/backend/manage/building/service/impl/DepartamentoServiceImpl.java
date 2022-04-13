package com.system.backend.manage.building.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.DepartamentoRespuesta;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.repository.IDepartamentoRepository;
import com.system.backend.manage.building.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private IDepartamentoRepository departamentorepository;

	@Override
	public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {

		Departamento departamento = mapearEntidad(departamentoDTO);

		Departamento nuevodeDepartamento = departamentorepository.save(departamento);

		DepartamentoDTO departamentorespuesta = mapearDTO(nuevodeDepartamento);

		return departamentorespuesta;

	}

	@Override
	public DepartamentoRespuesta obtenertodoslosDepartamento(int numeroDePagina, int medidaDePagina,String ordenarPor,String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina,sort);
		Page<Departamento> departamentos = departamentorepository.findAll(pageable);
		List<Departamento> listaDeDepartamentos = departamentos.getContent();
		List<DepartamentoDTO>contenido=listaDeDepartamentos.stream().map(departamento->mapearDTO(departamento)).collect(Collectors.toList());
		
		DepartamentoRespuesta departamentorespuesta= new DepartamentoRespuesta();
		departamentorespuesta.setContenido(contenido);
		departamentorespuesta.setNumeroDePagina(departamentos.getNumber());
		departamentorespuesta.setMedidaPagina(departamentos.getSize());
		departamentorespuesta.setTotalElementos(departamentos.getTotalElements());
		departamentorespuesta.setTotalPaginas(departamentos.getTotalPages());
		departamentorespuesta.setUltima(departamentos.isLast());
		return departamentorespuesta;


	}

	private Departamento mapearEntidad(DepartamentoDTO departamentoDTO) {

		Departamento departamento = new Departamento();

		departamento.setId(departamentoDTO.getId());
		departamento.setDepnumero(departamentoDTO.getDepnumero());
		departamento.setDeptelef(departamentoDTO.getDeptelef());
		return departamento;

	}

	private DepartamentoDTO mapearDTO(Departamento departamento) {
		DepartamentoDTO departamentoDTO = new DepartamentoDTO();

		departamentoDTO.setId(departamento.getId());
		departamentoDTO.setDepnumero(departamento.getDepnumero());
		departamentoDTO.setDeptelef(departamento.getDeptelef());
		return departamentoDTO;

	}

	@Override
	public DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow();
		departamento.setId(departamentoDTO.getId());
		departamento.setDepnumero(departamentoDTO.getDepnumero());
		departamento.setDeptelef(departamentoDTO.getDeptelef());
	

		Departamento departamentoactualizado = departamentorepository.save(departamento);
		return mapearDTO(departamentoactualizado);

	}

	@Override
	public DepartamentoDTO eliminarDepartamento(long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow();
		departamentorepository.delete(departamento);
		return mapearDTO(departamento);

	}

	@Override
	public DepartamentoDTO obtenerDepartamentosPorId(long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow();

		return mapearDTO(departamento);

	}

}
