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
import com.system.backend.manage.building.dto.entrada.PropietarioDTO;
import com.system.backend.manage.building.dto.entrada.PropietarioUpdate;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.PropietarioDepartamento;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.jsonignore.PropietarioIgnoreProperties;
import com.system.backend.manage.building.repository.IPropietarioRepository;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PropietarioServiceImpl implements PropietarioService {

	@Autowired
	private IPropietarioRepository propietarioRepositorio;
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PersonaService PersonaServ;
	@Override
	@Transactional
	public Propietario savePropietario(Propietario propietario) {
		return propietarioRepositorio.save(propietario);
	}
	
	@Override
	@Transactional
	public Propietario savePropietario(PropietarioDTO propietarioDTO) {
		
		PersonaServ.validateNewdni(propietarioDTO.getDni());
		Usuario newUser = 
				usuarioService.addNewUser(
						propietarioDTO.getNombre(), 
						propietarioDTO.getApellido(),
						propietarioDTO.getDni(), 
						propietarioDTO.getEmail(), 
						propietarioDTO.getPassword(),
						propietarioDTO.getIdPersonaRegistro());

		usuarioService.addRoleToUsuario(newUser.getEmail(), "ROLE_PROPIETARIO");

		Propietario propietario = new Propietario();
		propietario.setBirthdayDate(propietarioDTO.getBirthdayDate());
		propietario.setNumeroCelular(propietarioDTO.getNumeroCelular());
		propietario.setPersona(newUser.getPersona());
		Propietario nuevoPropietario = propietarioRepositorio.save(propietario);

		propietarioDTO.getDepartamentos().stream().forEach(x->{
			PropietarioDepartamento propietarioDepartamento=new PropietarioDepartamento();
			propietarioDepartamento.setDepartamento(x);
			propietarioDepartamento.setEstado(true);
			propietarioDepartamento.setPropietario(nuevoPropietario);
			nuevoPropietario.getPropietarioDepartamentos().add(propietarioDepartamento);
		});
		
		return nuevoPropietario;
	}

	@Override
	public PaginacionRespuesta listaPropietarios(int numeroDePagina, int medidaDePagina, String ordenarPor,
			String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

		Page<Propietario> propietarios = propietarioRepositorio.findAll(pageable);

		List<Propietario> listaDePropietarios = propietarios.getContent();
		List<PropietarioIgnoreProperties> contenido = listaDePropietarios
				.stream().map(p->{ 
					PropietarioIgnoreProperties obj=new PropietarioIgnoreProperties();
					obj.setId(p.getId());
					obj.setBirthdayDate(p.getBirthdayDate());
					obj.setNumeroCelular(p.getNumeroCelular());
					obj.setPersona(p.getPersona());
					return obj;
				}).collect(Collectors.toList());

		PaginacionRespuesta paginacion = new PaginacionRespuesta();
		paginacion.setContenido(contenido);
		paginacion.setNumeroDePagina(propietarios.getNumber());
		paginacion.setMedidaPagina(propietarios.getSize());
		paginacion.setTotalElementos(propietarios.getTotalElements());
		paginacion.setTotalPaginas(propietarios.getTotalPages());
		paginacion.setUltima(propietarios.isLast());

		return paginacion;
	}
	@Override
	public List<PropietarioIgnoreProperties> getAll() {
		
		List<Propietario> propietarios = propietarioRepositorio.findAll();

		List<PropietarioIgnoreProperties> lista = propietarios
				.stream().map(p->{ 
					PropietarioIgnoreProperties obj=new PropietarioIgnoreProperties();
					obj.setId(p.getId());
					obj.setBirthdayDate(p.getBirthdayDate());
					obj.setNumeroCelular(p.getNumeroCelular());
					obj.setPersona(p.getPersona());
					return obj;
				}).collect(Collectors.toList());


		return lista;
	}
	@Override
	public Propietario obtenerPropietarioPorId(long id) {
		Propietario propietario = propietarioRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"El propietario con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));

		return propietario;
	}

	@Override
	public List<Departamento> buscarDepartamentoXPropietario(long id) {
		log.info("PRIMEROOOOOOOOOOOOOOOOOOO");
		Propietario prop=obtenerPropietarioPorId(id);
		log.info("Antes");
		List<Departamento> listaDep = propietarioRepositorio.buscarDepartamentoXPropietario(prop.getId());
		log.info("PASO");
		return listaDep;
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

	@Override
	public Propietario changeActive(long id) {
		Propietario propietario=propietarioRepositorio.findById(id).orElseThrow(() -> new CustomAppException(
				"Propietario con id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));
		propietario.getPersona().setEstado(!propietario.getPersona().getEstado());
		
		return propietarioRepositorio.save(propietario);
	}


}
