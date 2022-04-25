package com.system.backend.manage.building.service.impl;

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
import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.DepartamentoRespuesta;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IDepartamentoRepository;
import com.system.backend.manage.building.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private IDepartamentoRepository departamentorepository;

	@Override
	@Transactional
	public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {
		
		validateNewDepNumero(departamentoDTO.getDepnumero());
		
		
		

		Departamento departamento = mapearEntidad(departamentoDTO);

		Departamento nuevodeDepartamento = departamentorepository.save(departamento);

		DepartamentoDTO departamentorespuesta = mapearDTO(nuevodeDepartamento);

		return departamentorespuesta;

	}
	private Departamento mapearEntidad(DepartamentoDTO departamentoDTO) {

		Departamento departamento = new Departamento();
		
		departamento.setId(departamentoDTO.getId());
		departamento.setDepnumero(departamentoDTO.getDepnumero());
		departamento.setDeptelef(departamentoDTO.getDeptelef());
		departamento.setPiso(departamentoDTO.getPiso());
		departamento.setAforo(departamentoDTO.getAforo());
		departamento.setEstado(departamentoDTO.getEstado());
		return departamento;

	}
	private DepartamentoDTO mapearDTO(Departamento departamento) {
		DepartamentoDTO departamentoDTO = new DepartamentoDTO();
		
		departamentoDTO.setId(departamento.getId());
		departamentoDTO.setDepnumero(departamento.getDepnumero());
		departamentoDTO.setDeptelef(departamento.getDeptelef());
		departamentoDTO.setPiso(departamento.getPiso());
		departamentoDTO.setAforo(departamento.getAforo());
		departamentoDTO.setEstado(departamento.getEstado());
		return departamentoDTO;

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
	@Override
	public DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow();
		departamento.setDepnumero(departamentoDTO.getDepnumero());
		departamento.setDeptelef(departamentoDTO.getDeptelef());
		departamento.setPiso(departamentoDTO.getPiso());
		departamento.setAforo(departamentoDTO.getAforo());
		
	

		Departamento departamentoactualizado = departamentorepository.save(departamento);
		return mapearDTO(departamentoactualizado);

	}

	@Override
	public DepartamentoDTO eliminarDepartamento(long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow();
		departamento.setEstado(false);
		//departamentorepository.delete(departamento);
		Departamento departamentoactualizado = departamentorepository.save(departamento);
		return mapearDTO(departamentoactualizado);

	}
	
	private Departamento findByDepnumero(String Depnumero) {
		return departamentorepository.findByDepnumero(Depnumero);
	}
	



	@Override
	public DepartamentoDTO obtenerDepartamentosPorId(long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow();

		return mapearDTO(departamento);

		
	}
	


	private boolean validateNewDepNumero(String string) {
		Departamento departamentoByNewDepnumemero = findByDepnumero(string);

		if (departamentoByNewDepnumemero != null) {
			throw new CustomAppException("El departamento ya existe en la Base de datos", 401,
					UserImplConstant.DEPARTAMENTO_EXISTENTE + " - " + string, "departamentexistexception",
					HttpStatus.CONFLICT);
		}
		return true;
	}
}

	
	

	
	



