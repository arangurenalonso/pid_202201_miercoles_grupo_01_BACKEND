package com.system.backend.manage.building;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.system.backend.manage.building.entity.Mascota;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.PropietarioService;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.system.backend.manage.building.dto.DepartamentoDTO;
import com.system.backend.manage.building.dto.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.MascotaCreateDTO;
import com.system.backend.manage.building.dto.PropietarioCreate;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.FamiliarService;
import com.system.backend.manage.building.service.MascotaService;
import com.system.backend.manage.building.service.UsuarioService;

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
//	@Bean
//    public Jackson2ObjectMapperBuilder jacksonBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return builder;
//    }

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "PUT", "POST",
				"DELETE", "PATCH", "OPTIONS");
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
	@Bean
	CommandLineRunner run(UsuarioService userService, PropietarioService propietarioService,
			PersonaService personaService, DepartamentoService departService, MascotaService mascotaService,
			FamiliarService familiarService) {
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
			Persona per5 = new Persona(null, "Kevin", "Ledesma", "78547878", true, new Date());
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
			
			
			departService.crearDepartamento(new DepartamentoDTO(null, "100", "902579922", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "101", "901547831", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "102", "903651875", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "103", "936427437", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "104", "9462131351", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "105", "9641318411", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "106", "9654112233", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "107", "9642311796", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "108", "9414111637", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "109", "9999999999", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "110", "9111111111", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "111", "9333244165", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "112", "9445556631", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "113", "9663332147", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "114", "9000345144", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "115", "9256314563", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "116", "9642136156", true));

			departService.crearDepartamento(new DepartamentoDTO(null, "117", "9541321354", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "118", "9666666666", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "119", "9563216112", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "120", "9202366325", true));

			departService.crearDepartamento(new DepartamentoDTO(null, "200", "902579922", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "201", "901547831", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "202", "903651875", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "203", "936427437", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "204", "9462131351", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "205", "9641318411", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "206", "9654112233", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "207", "9642311796", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "208", "9414111637", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "209", "9999999999", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "210", "9111111111", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "211", "9333244165", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "212", "9445556631", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "213", "9663332147", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "214", "9000345144", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "215", "9256314563", true));
			departService.crearDepartamento(new DepartamentoDTO(null, "216", "9642136156", true));

		};
	}
}