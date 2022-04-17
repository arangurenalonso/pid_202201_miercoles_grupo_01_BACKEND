package com.system.backend.manage.building.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.entity.Permiso;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IRoleRepository;
import com.system.backend.manage.building.repository.IUsuarioRepository;
import com.system.backend.manage.building.service.UsuarioService;
import java.util.Date;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Autowired
	private IRoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Usuario saveUser(Usuario user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return usuarioRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {		
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUsuario(String email, String roleName) {
		Usuario user = usuarioRepo.findByEmail(email);
		Role role = roleRepo.findByName(roleName);
		user.getPermiso().add(new Permiso(null, user, role));

	}

	@Override
	public Usuario getUser(String email) {
		// TODO Auto-generated method stub
		return usuarioRepo.findByEmail(email);
	}

	@Override
	public List<Usuario> getUsers() {
		// TODO Auto-generated method stub
		return usuarioRepo.findAll();
	}

	

	

	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepo.findByEmail(email);
	}

	@Override
	public Usuario addNewUser(String firstName, String lastName,String dni, String email, String password) {
		validateNewUsernameAndEmail(email);
		Persona persona = new Persona();
		persona.setNombre(firstName);
		persona.setApellido(lastName);
		persona.setDni(dni);
		persona.setEstado(true);
		persona.setCreateAt(new Date());

		Usuario user = new Usuario();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setPersona(persona);
		user.setLastLoginDateDisplay(new Date());
		user.setLastLoginDate(new Date());
		user.setActive(true);
		user.setNotLocked(true);

		Usuario newUser=usuarioRepo.save(user);

		return newUser;
	}
	
	private boolean validateNewUsernameAndEmail(String newEmail) {
		Usuario userByNewEmail = findByEmail(newEmail);

		if (userByNewEmail != null) {
			throw new CustomAppException("El usuario ya existe en la Base de datos", 401,
					UserImplConstant.EMAIL_ALREADY_EXISTS + " - " + newEmail, "UsernameExistException",
					HttpStatus.CONFLICT);
		}
		return true;
	}
	
}
