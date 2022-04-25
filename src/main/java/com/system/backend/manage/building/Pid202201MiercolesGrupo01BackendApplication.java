package com.system.backend.manage.building;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.MascotaCreateDTO;
import com.system.backend.manage.building.dto.PropietarioCreate;
import com.system.backend.manage.building.dto.VisitanteDTO;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.FamiliarService;
import com.system.backend.manage.building.service.MascotaService;
import com.system.backend.manage.building.service.UsuarioService;
import com.system.backend.manage.building.service.VisitanteService;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Pid202201MiercolesGrupo01BackendApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Pid202201MiercolesGrupo01BackendApplication.class, args);

	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "PUT", "POST",
				"DELETE", "PATCH", "OPTIONS");
	}

	@Bean
	CommandLineRunner run(UsuarioService userService, 
			PropietarioService propietarioService,
			PersonaService personaService,
			DepartamentoService departService,
			MascotaService mascotaService,
			FamiliarService familiarService,
			VisitanteService visitanteService ) {
		// TODO Auto-generated method stub
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_PROPIETARIO"));
			
			Persona per1 = new Persona(null, "Jhon", "Travolta", "99999999", true, new Date());
			Persona per2 = new Persona(null, "will", "Smith", "88888888", true, new Date());
			Persona per3 = new Persona(null, "jim", "Carry", "77777777", true, new Date());
			Persona per4 = new Persona(null, "arnold", "Schwarzenegger", "66666666", true, new Date());
			Persona per5 = new Persona(null, "Kevin", "Ledesma", "77547878", true, new Date());
			personaService.savePersona(per5);

			userService.saveUser(new Usuario(null, "john@gmail.com", "1234",null, per1, new Date(), new Date(), true,
					true, new ArrayList<>()));
			userService.saveUser(new Usuario(null,  "will@gmail.com", "1234",null, per2, new Date(), new Date(), true,
					true, new ArrayList<>()));
			userService.saveUser(new Usuario(null, "jim@gmail.com", "1234",null, per3, new Date(), new Date(), true,
					true, new ArrayList<>()));
			userService.saveUser(new Usuario(null,  "arnold@gmail.com", "1234",null, per4, new Date(), new Date(),
					true, true, new ArrayList<>()));

			userService.addRoleToUsuario("john@gmail.com", "ROLE_USER");
			userService.addRoleToUsuario("will@gmail.com", "ROLE_MANAGER");
			userService.addRoleToUsuario("jim@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold@gmail.com", "ROLE_SUPER_ADMIN");
			userService.addRoleToUsuario("arnold@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold@gmail.com", "ROLE_USER");
			 String sDate1="1992-07-17";  
			 Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			 String sDate2="1993-06-19";  
			 Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
			PropietarioCreate propietario1 = 
					new PropietarioCreate(null,date1, "997055037", "Jose Alonso", "Aranguren Martinez", "70919821","aranguren.alonso@gmail.com","12345");
			propietarioService.savePropietario(propietario1);
			PropietarioCreate propietario2 = 
					new PropietarioCreate(null, date2, "997061207", "Lisbeth", "Capcha Ramos", "73830389","capcha_lisbeth@gmail.com","12345");
			propietarioService.savePropietario(propietario2);

			MascotaCreateDTO mascota1 = new MascotaCreateDTO("perro", "lady", "dalmata");
			MascotaCreateDTO mascota2 = new MascotaCreateDTO( "perro","boby", "cruzado");
			mascotaService.crearMascota(mascota1,(long) 1);
			mascotaService.crearMascota(mascota2,(long) 1);

			 String sDate3="1959-02-11";  
			 Date date3=new SimpleDateFormat("yyyy-MM-dd").parse(sDate3);
			 
			FamiliarCreateDTO familiarNuevo1=new FamiliarCreateDTO(null,date3,"Padre","José Bernardo", "Aranguren Carvajal","08022057");
			FamiliarCreateDTO familiarNuevo2=new FamiliarCreateDTO(null,date3,"Madre","Vilma Gloria", "Martinez Dorival","08022056");
			familiarService.crearFamiliar(familiarNuevo1, (long)1);
			familiarService.crearFamiliar(familiarNuevo2, (long)1);
			
			
			VisitanteDTO visitanteDTO= new VisitanteDTO(null,"Armando", "Chancahuana Verdi","44875869");
			visitanteService.crearVisitante(visitanteDTO);
			
			departService.crearDepartamento(new DepartamentoDTO(null,"110","7851311",1,9,true));

		};
	}




	/*
	 * @Bean public CorsFilter corsFilter() { UrlBasedCorsConfigurationSource
	 * urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	 * CorsConfiguration corsConfiguration = new CorsConfiguration();
	 * corsConfiguration.setAllowCredentials(true);
	 * corsConfiguration.setAllowedOrigins(Collections.singletonList(
	 * "http://localhost:4200"));
	 * corsConfiguration.setAllowedHeaders(Arrays.asList("Origin",
	 * "Access-Control-Allow-Origin", "Content-Type", "Accept", "Jwt-Token",
	 * "Authorization", "Origin, Accept", "X-Requested-With",
	 * "Access-Control-Request-Method", "Access-Control-Request-Headers"));
	 * corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type",
	 * "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin",
	 * "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
	 * corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
	 * "DELETE", "OPTIONS"));
	 * urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",
	 * corsConfiguration); return new CorsFilter(urlBasedCorsConfigurationSource); }
	 */
}