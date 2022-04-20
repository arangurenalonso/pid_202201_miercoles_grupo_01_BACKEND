package com.system.backend.manage.building.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.PropietarioCreate;
import com.system.backend.manage.building.dto.PropietarioRespuesta;
import com.system.backend.manage.building.dto.PropietarioUpdate;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IPropietarioRepository;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.service.UsuarioService;

@Service
public class PropietarioServiceImpl implements PropietarioService {

	@Autowired
	private IPropietarioRepository propietarioRepositorio;
	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional
	public Propietario savePropietario(PropietarioCreate propietarioDTO) {

		Usuario newUser = usuarioService.addNewUser(propietarioDTO.getNombre(), propietarioDTO.getApellido(),
				propietarioDTO.getDni(), propietarioDTO.getEmail(), propietarioDTO.getPassword());

		usuarioService.addRoleToUsuario(newUser.getEmail(), "ROLE_PROPIETARIO");

		Propietario propietario = new Propietario();
		propietario.setBirthdayDate(propietarioDTO.getBirthdayDate());
		propietario.setNumeroCelular(propietarioDTO.getNumeroCelular());
		propietario.setPersona(newUser.getPersona());
		Propietario nuevoPropietario = propietarioRepositorio.save(propietario);

		return nuevoPropietario;
	}

	@Override
	public PropietarioRespuesta listaPropietarios(int numeroDePagina, int medidaDePagina, String ordenarPor,
			String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

		Page<Propietario> propietarios = propietarioRepositorio.findAll(pageable);

		List<Propietario> listaDePropietarios = propietarios.getContent();
		// List<PropietarioDTO> contenido = listaDePropietarios.stream().map(propietario
		// -> mapearDTO(propietario)).collect(Collectors.toList());

		PropietarioRespuesta propietarioRespuesta = new PropietarioRespuesta();
		propietarioRespuesta.setContenido(listaDePropietarios);
		propietarioRespuesta.setNumeroPagina(propietarios.getNumber());
		propietarioRespuesta.setMedidaPagina(propietarios.getSize());
		propietarioRespuesta.setTotalElementos(propietarios.getTotalElements());
		propietarioRespuesta.setTotalPaginas(propietarios.getTotalPages());
		propietarioRespuesta.setUltima(propietarios.isLast());

		return propietarioRespuesta;
	}

	@Override
	public Propietario obtenerPropietarioPorId(long id) {
		Propietario propietario = propietarioRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"El propietario con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));

		return propietario;
	}

	@Override
	public Propietario actualizarPropietario(PropietarioUpdate propietariUpdate, long id) {
		Propietario propietario = this.obtenerPropietarioPorId(id);

		propietario.getPersona().setNombre(propietariUpdate.getNombre());
		propietario.getPersona().setApellido(propietariUpdate.getApellido());
		propietario.getPersona().setDni(propietariUpdate.getDni());

		propietario.setBirthdayDate(propietariUpdate.getBirthdayDate());
		propietario.setNumeroCelular(propietariUpdate.getNumeroCelular());

		Propietario propietarioActualizado = propietarioRepositorio.save(propietario);

		return propietarioActualizado;
	}

	@Override
	public Propietario eliminarPropietario(long id) {
		Propietario propietario = propietarioRepositorio.findById(id).orElseThrow();
		propietario.getPersona().setEstado(false);
		propietario.getPersona().getUsuario().setActive(false);
		Propietario propietarioActualizado = propietarioRepositorio.save(propietario);

		return propietarioActualizado;
	}

}
