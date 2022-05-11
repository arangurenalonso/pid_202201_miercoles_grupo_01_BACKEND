package com.system.backend.manage.building.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.UpdatePropietarioDepartamentosDTO;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.PropietarioDepartamento;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.jsonignore.DepartamentoIgnoreProperties;
import com.system.backend.manage.building.repository.IPropietarioDepartamentoRepository;
import com.system.backend.manage.building.repository.IPropietarioRepository;
import com.system.backend.manage.building.service.PropietarioDepartamentoService;
@Service
public class PropietarioDepartamentoServiceImpl implements PropietarioDepartamentoService {

	@Autowired
	private IPropietarioRepository propietarioRepositorio;
	@Autowired
	private IPropietarioDepartamentoRepository propietarioDepartamentoRepository;

	@Override
	@Transactional
	public List<PropietarioDepartamento> actualizarDepartamentosPropietario(long id_propietario,
			UpdatePropietarioDepartamentosDTO updatePropietarioDepartamentosDTO) {

		Propietario propietario = propietarioRepositorio.findById(id_propietario)
				.orElseThrow(() -> new CustomAppException(
						"El propietario con id '" + id_propietario + "' no existe en la Base de datos", 400,
						UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException",
						HttpStatus.BAD_REQUEST));

		List<PropietarioDepartamento> propietarioDepartamentosEncontrado = propietarioDepartamentoRepository
				.buscarDepartamentosDelPropietario(id_propietario);
		
		List<PropietarioDepartamento> listPropietarioDepartamentoAgregar = new ArrayList<>();
		List<PropietarioDepartamento> listPropietarioDepartamentoActualizar = new ArrayList<>();
		
		
		boolean aux = true;
		
		for (DepartamentoIgnoreProperties depCrear : updatePropietarioDepartamentosDTO.getDepartamentosCrear()) {
			aux=true;
			PropietarioDepartamento propDep = null;
			for (PropietarioDepartamento y : propietarioDepartamentosEncontrado) {
				if (y.getDepartamento().getId() == depCrear.getId()) {
					propDep=y;
					aux=false;
				}
			} 
			if(aux) {
				PropietarioDepartamento propietarioDepartamento = new PropietarioDepartamento();
				propietarioDepartamento.setDepartamento(depCrear);
				propietarioDepartamento.setEstado(true);
				propietarioDepartamento.setPropietario(propietario);
				listPropietarioDepartamentoAgregar.add(propietarioDepartamento);
			}else {
				propDep.setEstado(true);
				listPropietarioDepartamentoActualizar.add(propDep);
			}
		}

		updatePropietarioDepartamentosDTO.getDepartamentosDesactivar().stream().forEach(x -> {
			propietarioDepartamentosEncontrado.stream().forEach(y -> {
				if (y.getDepartamento().getId() == x.getId()) {
					y.setEstado(false);
					listPropietarioDepartamentoActualizar.add(y);
				}

			});
		});
		propietarioDepartamentoRepository.saveAll(listPropietarioDepartamentoAgregar);
		propietarioDepartamentoRepository.saveAll(listPropietarioDepartamentoActualizar);
		List<PropietarioDepartamento> propDep = propietarioDepartamentoRepository
				.buscarDepartamentosDelPropietario(id_propietario);
		return propDep;

	}

}
