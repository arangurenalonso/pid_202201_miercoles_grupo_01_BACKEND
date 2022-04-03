package com.system.backend.manage.building.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.entity.Rol;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.repository.IRoleRepository;
import com.system.backend.manage.building.repository.IUsuarioRepository;
import com.system.backend.manage.building.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	@Autowired
	private IRoleRepository roleRepo;
	
	
	@Override
	public Usuario saveUser(Usuario user) {
		// TODO Auto-generated method stub
		log.info("Saving new user {} to the database",user.getName());
		return usuarioRepo.save(user);
	}

	@Override
	public Rol saveRole(Rol role) {
		// TODO Auto-generated method stub
		log.info("Saving new role {} to the database",role.getNombre());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUsuario(String username, String roleName) {
		log.info("Adding role {} to user {} ",roleName,username);
		Usuario user=usuarioRepo.findByUsername(username);
		Rol role=roleRepo.findByNombre(roleName);
		user.getRoles().add(role);
		
	}

	@Override
	public Usuario getUser(String username) {
		// TODO Auto-generated method stub
		log.info("Fetching user {}",username);
		return usuarioRepo.findByUsername(username);
	}

	@Override
	public List<Usuario> getUsers() {
		// TODO Auto-generated method stub
		log.info("Fetching All users");
		return usuarioRepo.findAll();
	}

}
