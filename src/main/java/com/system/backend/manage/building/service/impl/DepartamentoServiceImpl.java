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
import com.system.backend.manage.building.dto.entrada.DepartamentoDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IDepartamentoRepository;
import com.system.backend.manage.building.repository.IPersonaRepository;
import com.system.backend.manage.building.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private IDepartamentoRepository departamentorepository;
	@Autowired
	private IPersonaRepository personaRepo;
	
	@Override
	@Transactional
	public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {
		Persona personaRegistro=personaRepo.findById(departamentoDTO.getIdPersonaRegistro()).orElseThrow(() -> new CustomAppException(
				"La persona con id '" + departamentoDTO.getIdPersonaRegistro() + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));

		
		validateNewDepNumero(departamentoDTO.getDepnumero());
		
		
		Departamento departamento = mapearEntidad(departamentoDTO);
		departamento.setPersonaRegistro(personaRegistro);
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
	public PaginacionRespuesta obtenertodoslosDepartamento(int numeroDePagina, int medidaDePagina,String ordenarPor,String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina,sort);
		Page<Departamento> departamentos = departamentorepository.findAll(pageable);
		List<Departamento> listaDeDepartamentos = departamentos.getContent();
		
		PaginacionRespuesta paginacion= new PaginacionRespuesta();
		paginacion.setContenido(listaDeDepartamentos);
		paginacion.setNumeroDePagina(departamentos.getNumber());
		paginacion.setMedidaPagina(departamentos.getSize());
		paginacion.setTotalElementos(departamentos.getTotalElements());
		paginacion.setTotalPaginas(departamentos.getTotalPages());
		paginacion.setUltima(departamentos.isLast());
		return paginacion;


	}
	@Override
	public DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El Departamento con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		departamento.setDepnumero(departamentoDTO.getDepnumero());
		departamento.setDeptelef(departamentoDTO.getDeptelef());
		departamento.setPiso(departamentoDTO.getPiso());
		departamento.setAforo(departamentoDTO.getAforo());
		
	

		Departamento departamentoactualizado = departamentorepository.save(departamento);
		return mapearDTO(departamentoactualizado);

	}

	@Override
	public DepartamentoDTO eliminarDepartamento(long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El Departamento con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		departamento.setEstado(false);
		
		//departamentorepository.delete(departamento);
		Departamento departamentoactualizado = departamentorepository.save(departamento);
		return mapearDTO(departamentoactualizado);

	}
	
	private Departamento findByDepnumero(String Depnumero) {
		return departamentorepository.findByDepnumero(Depnumero);
	}
	



	@Override
	public Departamento obtenerDepartamentosPorId(long id) {
		Departamento departamento = departamentorepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El Departamento con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		return departamento;

		
	}
	@Override
	public List<Departamento> listaDepartamentoDisponibles(){
		List<Departamento> listaTodoDepartamento=departamentorepository.findAll();
		
		List<Departamento> departamentosDisponibles=listaTodoDepartamento
											.stream()
											.filter(x->(
	x.getPropietarioDepartamentos().size()==0 ||
	x.getPropietarioDepartamentos().stream().filter(y->y.getEstado() ==true).collect(Collectors.toList()).size()==0
													))
													.collect(Collectors.toList());
		
		return departamentosDisponibles;
	}
	
	@Override
	public List<Departamento> listaTodosDepartamnetos() {
		// TODO Auto-generated method stub
		return departamentorepository.findAll();
	}

	private boolean validateNewDepNumero(String numDepartamento) {
		Departamento departamentoByNewDepnumemero = findByDepnumero(numDepartamento);

		if (departamentoByNewDepnumemero != null) {
			throw new CustomAppException("El departamento ya existe en la Base de datos", 409,
					UserImplConstant.DEPARTAMENTO_EXISTENTE(numDepartamento) , "departamentexistexception",
					HttpStatus.CONFLICT);
		}
		return true;
	}
	@Override
	public Departamento changeActive(long id) {
		Departamento departamento=departamentorepository.findById(id).orElseThrow(() -> new CustomAppException(
				"Departamento con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		departamento.setEstado(!departamento.getEstado());
		
		return departamentorepository.save(departamento);
	}
	
	
}


	
	

	
	



