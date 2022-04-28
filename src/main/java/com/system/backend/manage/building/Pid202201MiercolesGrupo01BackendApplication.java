package com.system.backend.manage.building;

import java.util.Date;
import java.util.HashSet;
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
import com.system.backend.manage.building.jsonignore.DepartamentoIgnoreProperties;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.MascotaDTO;
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
			Persona per5 = new Persona(null, "admin", "master", "00000000", true, new Date());
			personaService.savePersona(per5);
			
			userService.saveUser(new Usuario(null, "john@gmail.com", "1234", null, per1, new Date(), new Date(), true,
					true, new HashSet<>()));
			userService.saveUser(new Usuario(null,  "will@gmail.com", "1234",null, per2, new Date(), new Date(), true,
					true,new HashSet<>()));
			userService.saveUser(new Usuario(null, "jim@gmail.com", "1234",null, per3, new Date(), new Date(), true,
					true, new HashSet<>()));
			userService.saveUser(new Usuario(null,  "arnold@gmail.com", "1234",null, per4, new Date(), new Date(),
					true, true, new HashSet<>()));

			userService.addRoleToUsuario("john@gmail.com", "ROLE_USER");
			userService.addRoleToUsuario("will@gmail.com", "ROLE_MANAGER");
			userService.addRoleToUsuario("jim@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold@gmail.com", "ROLE_SUPER_ADMIN");
			userService.addRoleToUsuario("arnold@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUsuario("arnold@gmail.com", "ROLE_USER");
			 String sDate1="1992-07-17";  
			 Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			
			

			
			 String sDate3="1959-02-11";  
			 Date date3=new SimpleDateFormat("yyyy-MM-dd").parse(sDate3);
			 
			
			
			VisitanteDTO visitanteDTO= new VisitanteDTO(null,"Armando", "Chancahuana Verdi","44875869",(long)3);
			visitanteService.crearVisitante(visitanteDTO);
			

			departService.crearDepartamento(new DepartamentoDTO(null,"110","7851311",1,2,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"111","7333311",1,3,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"112","7869311",1,3,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"115","7777771",1,5,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"301","7001564",3,1,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"302","7331564",3,2,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"303","7201564",3,2,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"304","7101564",3,7,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"520","7851011",5,4,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"521","7853211",5,5,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"523","7856911",5,2,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"515","7821316",5,3,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"715","7693213",7,2,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"716","7456631",7,3,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"717","7315567",7,1,true,(long)1));
            departService.crearDepartamento(new DepartamentoDTO(null,"718","7013463",7,3,true,(long)1));

           ArrayList<DepartamentoIgnoreProperties> departamento=   new ArrayList<>();
           DepartamentoIgnoreProperties depIg=new DepartamentoIgnoreProperties();
           depIg.setId((long)1);
           departamento.add(depIg);
            PropietarioCreate propietario1 = 
					new PropietarioCreate(null,date1, "997055037", "Jose Alonso", "Aranguren Martinez", "70919821","aranguren.alonso@gmail.com","12345",(long)2,departamento);
			propietarioService.savePropietario(propietario1);

			FamiliarCreateDTO familiarNuevo1=new FamiliarCreateDTO(null,date3,"Padre","José Bernardo", "Aranguren Carvajal","08022057",(long)4);
			FamiliarCreateDTO familiarNuevo2=new FamiliarCreateDTO(null,date3,"Madre","Vilma Gloria", "Martinez Dorival","08022056",(long)4);
			familiarService.crearFamiliar(familiarNuevo1, (long)1);
			familiarService.crearFamiliar(familiarNuevo2, (long)1);
			
			MascotaDTO mascota1 = new MascotaDTO(null,"perro", "lady", "dalmata",(long)1);
			MascotaDTO mascota2 = new MascotaDTO( null,"perro","boby", "cruzado",(long)1);
			mascotaService.crearMascota(mascota1,(long) 1);
			mascotaService.crearMascota(mascota2,(long) 1);
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