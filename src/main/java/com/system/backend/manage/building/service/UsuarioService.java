package com.system.backend.manage.building.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;

public interface UsuarioService {
	Usuario saveUser(Usuario user);

	Role saveRole(Role role);

	void addRoleToUsuario(String username, String roleName);

	Usuario getUser(String username);

	List<Usuario> getUsers();

	Usuario addNewUser(String firstName, String lastName, String username, String email,String password, String role,
			boolean isNonLocked, boolean isActive, MultipartFile profileImage);

	Usuario findByUsername(String username);

	Usuario findByEmail(String email);
}
