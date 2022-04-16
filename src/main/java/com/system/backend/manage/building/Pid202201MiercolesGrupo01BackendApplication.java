package com.system.backend.manage.building;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.PropietarioDTO;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.UsuarioService;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Pid202201MiercolesGrupo01BackendApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(Pid202201MiercolesGrupo01BackendApplication.class, args);
		
	}
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins(
	                        "http://localhost:4200"
	                )
	                .allowedMethods(
	                        "GET",
	                        "PUT",
	                        "POST",
	                        "DELETE",
	                        "PATCH",
	                        "OPTIONS"
	                );
	    }
	/*
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
	*/
	@Bean
	CommandLineRunner run (UsuarioService userService,PropietarioService propietarioService,
			PersonaService personaService,DepartamentoService departService) {
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
			
			userService.saveUser(new Usuario(null,"john","john@gmail.com","1234",per1,new Date(),new Date(),true,true,new ArrayList<>()));
			userService.saveUser(new Usuario(null,"will","will@gmail.com","1234",per2,new Date(),new Date(),true,true,new ArrayList<>()));
			userService.saveUser(new Usuario(null,"jim","jim@gmail.com","1234",per3,new Date(),new Date(),true,true,new ArrayList<>()));
			userService.saveUser(new Usuario(null,"arnold","arnold@gmail.com","1234",per4,new Date(),new Date(),true,true,new ArrayList<>()));

			userService.addRoleToUsuario("John", "ROLE_USER");
			userService.addRoleToUsuario("will", "ROLE_MANAGER");
			userService.addRoleToUsuario("jim", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold", "ROLE_USER");
			
			PropietarioDTO propietario1 = new PropietarioDTO(null,new Date(),"","989777585",per1,true);
			propietarioService.savePropietario(propietario1);
			
			Persona per5 = new Persona(null,"Kevin","Ledesma","78547878",true,new Date());
			personaService.savePersona(per5);
			
			PropietarioDTO propietario2 = new PropietarioDTO(null,new Date(),"","969696969",per5,true);
			propietarioService.savePropietario(propietario2);
			
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