package com.system.backend.manage.building.service;

import java.util.List;

import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;

public interface UsuarioService {
	Usuario saveUser(Usuario user);
	Role saveRole(Role role);
	void addRoleToUsuario(String username,String roleName);
	Usuario getUser(String username);
	List<Usuario> getUsers();
}
