package com.system.backend.manage.building.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.excepciones.ResourceNotFoundException;
import com.system.backend.manage.building.repository.IDepartamentoRepository;
import com.system.backend.manage.building.service.DepartamentoService;

public class DepartamentoServiceImpl implements DepartamentoService {
	
	@Autowired
	private IDepartamentoRepository departamentorepository;

	@Override
	public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {
		
		Departamento departamento = mapearEntidad(departamentoDTO);
		
		Departamento nuevodeDepartamento=departamentorepository.save(departamento);
		
		DepartamentoDTO departamentorespuesta = mapearDTO(nuevodeDepartamento);
		
		return departamentorespuesta;
		
	
	
	}


	
	private Departamento mapearEntidad(DepartamentoDTO departamentoDTO){
		
		Departamento departamento = new Departamento();
		
		departamento.setIdDepartamento(departamentoDTO.getIdDepartamento());
		departamento.setDepmetrosConst(departamentoDTO.getDepmetrosConst());
		departamento.setDepniveles(departamentoDTO.getDepniveles());
		departamento.setDepcuartos(departamentoDTO.getDepcuartos());
		departamento.setDepbaños(departamentoDTO.getDepbaños());
		departamento.setDepnumero(departamentoDTO.getDepnumero());
		departamento.setDepcochera(departamentoDTO.getDepcochera());
		departamento.setIdpropetario(departamentoDTO.getIdpropetario());
		return departamento;
		
		
		
		
	}


	@Override
	public DepartamentoDTO obtenerDepartamentosPorId(long id) {
		Departamento departamento=departamentorepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Departamento","idDepartamento",idDepartamento));
		return mapearDTO(departamento);
	}

	@Override
	public List<DepartamentoDTO> obtenertodoslosDepartamento() {
	    List<Departamento>departamentos= departamentorepository.findAll();
	    
			return departamentos.stream().map(departamento -> mapearDTO(departamento)).collect(Collectors.toList());
		}
		private DepartamentoDTO mapearDTO(Departamento departamento) {
			DepartamentoDTO departamentoDTO = new DepartamentoDTO();
				
			departamentoDTO.setIdDepartamento(departamento.getIdDepartamento());
			departamentoDTO.setDepmetrosConst(departamento.getDepmetrosConst());
			departamentoDTO.setDepniveles(departamento.getDepniveles());
			departamentoDTO.setDepcuartos(departamento.getDepcuartos());
			departamentoDTO.setDepbaños(departamento.getDepbaños());
			departamentoDTO.setDepnumero(departamento.getDepnumero());
			departamentoDTO.setDepcochera(departamento.getDepcochera());
			departamentoDTO.setIdpropetario(departamento.getIdpropetario());
			return departamentoDTO;
			
	}



}
