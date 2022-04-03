package com.system.backend.manage.building;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.system.backend.manage.building.entity.Rol;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.UsuarioService;

@SpringBootApplication
public class Pid202201MiercolesGrupo01BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pid202201MiercolesGrupo01BackendApplication.class, args);
	}
	@Bean
	CommandLineRunner run (UsuarioService userService) {
		// TODO Auto-generated method stub
		return args -> {
			userService.saveRole(new Rol(null,"ROLE_USER"));
			userService.saveRole(new Rol(null,"ROLE_MANAGER"));
			userService.saveRole(new Rol(null,"ROLE_ADMIN"));
			userService.saveRole(new Rol(null,"ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new Usuario(null,"John Travolta","john","john@gmail.com","1234",new ArrayList<>()));
			userService.saveUser(new Usuario(null,"Will Smith","will","will@gmail.com","1234",new ArrayList<>()));
			userService.saveUser(new Usuario(null,"Jim Carry","jim","jim@gmail.com","1234",new ArrayList<>()));
			userService.saveUser(new Usuario(null,"Arnold Schwarsenegger","arnold","arnold@gmail.com","1234",new ArrayList<>()));

			userService.addRoleToUsuario("John", "ROLE_USER");
			userService.addRoleToUsuario("will", "ROLE_MANAGER");
			userService.addRoleToUsuario("jim", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_USER");
		};
	}
}
