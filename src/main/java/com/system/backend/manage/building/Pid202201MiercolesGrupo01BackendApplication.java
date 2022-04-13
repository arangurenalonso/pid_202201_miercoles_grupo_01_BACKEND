package com.system.backend.manage.building;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.Rol;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.UsuarioService;

@SpringBootApplication
public class Pid202201MiercolesGrupo01BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pid202201MiercolesGrupo01BackendApplication.class, args);
	}
	@Bean
	CommandLineRunner run (UsuarioService userService,DepartamentoService departService) {
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
			
			
		   departService.crearDepartamento(new DepartamentoDTO(1,"100","902579922"));
		   departService.crearDepartamento(new DepartamentoDTO(2,"101","901547831"));
		   departService.crearDepartamento(new DepartamentoDTO(3,"102","903651875"));
		   departService.crearDepartamento(new DepartamentoDTO(4,"103","936427437"));
		   departService.crearDepartamento(new DepartamentoDTO(5,"104","9462131351"));
		   departService.crearDepartamento(new DepartamentoDTO(6,"105","9641318411"));
		   departService.crearDepartamento(new DepartamentoDTO(7,"106","9654112233"));
		   departService.crearDepartamento(new DepartamentoDTO(8,"107","9642311796"));
		   departService.crearDepartamento(new DepartamentoDTO(9,"108","9414111637"));
		   departService.crearDepartamento(new DepartamentoDTO(10,"109","9999999999"));
		   departService.crearDepartamento(new DepartamentoDTO(11,"110","9111111111"));
		   departService.crearDepartamento(new DepartamentoDTO(12,"111","9333244165"));
		   departService.crearDepartamento(new DepartamentoDTO(13,"112","9445556631"));
		   departService.crearDepartamento(new DepartamentoDTO(14,"113","9663332147"));
		   departService.crearDepartamento(new DepartamentoDTO(15,"114","9000345144"));
		   departService.crearDepartamento(new DepartamentoDTO(16,"115","9256314563"));
		   departService.crearDepartamento(new DepartamentoDTO(17,"116","9642136156"));
		   
		   departService.crearDepartamento(new DepartamentoDTO(18,"117","9541321354"));
		   departService.crearDepartamento(new DepartamentoDTO(19,"118","9666666666"));
		   departService.crearDepartamento(new DepartamentoDTO(20,"119","9563216112"));
		   departService.crearDepartamento(new DepartamentoDTO(21,"120","9202366325"));
		   
		   departService.crearDepartamento(new DepartamentoDTO(23,"",""));

			
			
		};
	}
}