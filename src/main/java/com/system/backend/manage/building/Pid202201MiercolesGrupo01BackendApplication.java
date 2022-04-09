package com.system.backend.manage.building;

import java.util.Date;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Propietario;
import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.service.UsuarioService;

@SpringBootApplication

public class Pid202201MiercolesGrupo01BackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Pid202201MiercolesGrupo01BackendApplication.class, args);
		
	}
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run (UsuarioService userService, 
			PropietarioService propietarioService,
			PersonaService personaService) {
		// TODO Auto-generated method stub
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
			
			
			Persona per1=new Persona(null,"Jhon","Travolta","99999999",true,new Date());
			Persona per2=new Persona(null,"will","Smith","88888888",true,new Date());
			Persona per3=new Persona(null,"jim","Carry","77777777",true,new Date());
			Persona per4=new Persona(null,"arnold","Schwarzenegger","66666666",true,new Date());
			
			userService.saveUser(new Usuario(null,"john","john@gmail.com","1234",per1,new ArrayList<>()));
			userService.saveUser(new Usuario(null,"will","will@gmail.com","1234",per2,new ArrayList<>()));
			userService.saveUser(new Usuario(null,"jim","jim@gmail.com","1234",per3,new ArrayList<>()));
			userService.saveUser(new Usuario(null,"arnold","arnold@gmail.com","1234",per4,new ArrayList<>()));

			userService.addRoleToUsuario("John", "ROLE_USER");
			userService.addRoleToUsuario("will", "ROLE_MANAGER");
			userService.addRoleToUsuario("jim", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_USER");
			
			Propietario propietario1 = new Propietario(null,new Date(),"","989777585",per1);
			propietarioService.savePropietario(propietario1);
			
			Persona per5 = new Persona(null,"Kevin","Ledesma","78547878",true,new Date());
			personaService.savePersona(per5);
			
			//Propietario propietario2 = new Propietario(null,new Date(),"","969696969",per5);
			//propietarioService.savePropietario(propietario2);
			
		};
	}
}
