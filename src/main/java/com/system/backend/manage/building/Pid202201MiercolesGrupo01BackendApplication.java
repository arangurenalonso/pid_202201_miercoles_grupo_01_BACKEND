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

import com.system.backend.manage.building.dto.entrada.DepartamentoDTO;
import com.system.backend.manage.building.dto.entrada.FamiliarCreateDTO;
import com.system.backend.manage.building.dto.entrada.IncidenteDTO;
import com.system.backend.manage.building.dto.entrada.MascotaDTO;
import com.system.backend.manage.building.dto.entrada.ProgramacionPagosDTO;
import com.system.backend.manage.building.dto.entrada.PropietarioDTO;
import com.system.backend.manage.building.dto.entrada.ServicioDTO;
import com.system.backend.manage.building.dto.entrada.VisitaDTO;
import com.system.backend.manage.building.dto.entrada.VisitanteDTO;
import com.system.backend.manage.building.entity.Persona;
import com.system.backend.manage.building.entity.Role;
import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.jsonignore.DepartamentoIgnoreProperties;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.ProgramacionPagosService;
import com.system.backend.manage.building.service.PropietarioService;
import com.system.backend.manage.building.service.ServicioService;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.FamiliarService;
import com.system.backend.manage.building.service.IncidenteService;
import com.system.backend.manage.building.service.MascotaService;
import com.system.backend.manage.building.service.UsuarioService;
import com.system.backend.manage.building.service.VisitaService;
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
			VisitanteService visitanteService,
			VisitaService visitaService,
			ServicioService servicioService,
			IncidenteService incidenteService,
			ProgramacionPagosService programacionPagosService) {
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
			VisitanteDTO visitanteDTO1= new VisitanteDTO(null,"Teresa ", "Crosby","75487857",(long)3);
			VisitanteDTO visitanteDTO2= new VisitanteDTO(null,"James", "Berry","85744744",(long)3);
			VisitanteDTO visitanteDTO3= new VisitanteDTO(null,"Stephen ", "Velez","45788897",(long)3);
			VisitanteDTO visitanteDTO4= new VisitanteDTO(null,"Denise ", "Garza","74877874",(long)3);
			VisitanteDTO visitanteDTO5= new VisitanteDTO(null,"Jeremy ", "Rodriguez","74258612",(long)3);
			VisitanteDTO visitanteDTO6= new VisitanteDTO(null,"Scott ", "Morgan","74578455",(long)3);
			visitanteService.crearVisitante(visitanteDTO);
			visitanteService.crearVisitante(visitanteDTO1);
			visitanteService.crearVisitante(visitanteDTO2);
			visitanteService.crearVisitante(visitanteDTO3);
			visitanteService.crearVisitante(visitanteDTO4);
			visitanteService.crearVisitante(visitanteDTO5);
			visitanteService.crearVisitante(visitanteDTO6);
			

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

           ArrayList<DepartamentoIgnoreProperties> departamentos1=   new ArrayList<>();
           DepartamentoIgnoreProperties depIg1=new DepartamentoIgnoreProperties();
           depIg1.setId((long)1);
           DepartamentoIgnoreProperties depIg2=new DepartamentoIgnoreProperties();
           depIg2.setId((long)2);
           departamentos1.add(depIg1);
           departamentos1.add(depIg2);
           
           ArrayList<DepartamentoIgnoreProperties> departamentos2=   new ArrayList<>();
           DepartamentoIgnoreProperties depIg3=new DepartamentoIgnoreProperties();
           depIg3.setId((long)3);
           departamentos2.add(depIg3);
           
           ArrayList<DepartamentoIgnoreProperties> departamentos3=   new ArrayList<>();
           DepartamentoIgnoreProperties depIg4=new DepartamentoIgnoreProperties();
           depIg4.setId((long)4);
           departamentos3.add(depIg4);
           
           ArrayList<DepartamentoIgnoreProperties> departamentos4=   new ArrayList<>();
           DepartamentoIgnoreProperties depIg5=new DepartamentoIgnoreProperties();
           depIg5.setId((long)5);
           departamentos4.add(depIg5);
           
            PropietarioDTO propietario1 = 
					new PropietarioDTO(null,date1, "997055037", "Jose Alonso", "Aranguren Martinez", "70919821","aranguren.alonso@gmail.com","12345",(long)2,departamentos1);
			propietarioService.savePropietario(propietario1);

			PropietarioDTO propietario2 = 
					new PropietarioDTO(null,date1, "997755154", "Marco", "Aurelio Gacia", "44565251","marco.aurelio@gmail.com","12345",(long)2,departamentos2);
			propietarioService.savePropietario(propietario2);
			
			PropietarioDTO propietario3 = 
					new PropietarioDTO(null,date1, "998653259", "Juan Carlos", "Jhamideh", "45655644","juan.jhamideth@gmail.com","12345",(long)2,departamentos3);
			propietarioService.savePropietario(propietario3);
			
			PropietarioDTO propietario4 = 
					new PropietarioDTO(null,date1, "999888757", "Jose Luis", "Martinez", "78754777","jose.luis@gmail.com","12345",(long)2,departamentos4);
			propietarioService.savePropietario(propietario4);
			
			FamiliarCreateDTO familiarNuevo1=new FamiliarCreateDTO(null,date3,"Padre","José Bernardo", "Aranguren Carvajal","08022057",(long)4);
			FamiliarCreateDTO familiarNuevo2=new FamiliarCreateDTO(null,date3,"Madre","Vilma Gloria", "Martinez Dorival","08022056",(long)4);
			familiarService.crearFamiliar(familiarNuevo1, (long)1);
			familiarService.crearFamiliar(familiarNuevo2, (long)1);
			
			MascotaDTO mascota1 = new MascotaDTO(null,"perro", "lady", "dalmata",(long)1);
			MascotaDTO mascota2 = new MascotaDTO( null,"perro","boby", "cruzado",(long)1);
			mascotaService.crearMascota(mascota1,(long) 1);
			mascotaService.crearMascota(mascota2,(long) 1);
			
			VisitaDTO visita1=new VisitaDTO(null,new Date(),null,"Visita a amigos",null,(long)1,(long)1,(long)1,(long)1);
			visitaService.registrarVisita(visita1);
		
			VisitaDTO visita2=new VisitaDTO(null,new Date(),null,"Analizar inventario",null,(long)2,(long)2,(long)2,(long)1);
			visitaService.registrarVisita(visita2);

			VisitaDTO visita4=new VisitaDTO(null,new Date(),null,"Reunion de estudio",null,(long)3,(long)3,(long)3,(long)1);
			visitaService.registrarVisita(visita4);
			VisitaDTO visita5=new VisitaDTO(null,new Date(),null,"Reunion de Canto",null,(long)4,(long)4,(long)4,(long)1);
			visitaService.registrarVisita(visita5);
			
			
			ServicioDTO servicio1=new ServicioDTO(null,"Luz","Servicio de Luz que se consume en las áreas comunes",10.50,(long)1);
			ServicioDTO servicio2=new ServicioDTO(null,"Agua","Servicio de Agua que se consume en las áreas comunes",12.50,(long)1);
			ServicioDTO servicio3=new ServicioDTO(null,"Trio Movistar","Servicio de movistar Internet/Cable/Telefono",35.70,(long)1);
			ServicioDTO servicio4=new ServicioDTO(null,"Ascensor","Cuota mensual para el mantenimiento al Ascensor",35.70,(long)1);
			ServicioDTO servicio5=new ServicioDTO(null,"Vigilancia","Servicio de Vigilancia y control de entrada",50.00,(long)1);
			ServicioDTO servicio6=new ServicioDTO(null,"Limpieza","Servicio de limpieza de los ambientes comunes",40.00,(long)1);
			
			servicioService.registrar(servicio1);
			servicioService.registrar(servicio2);
			servicioService.registrar(servicio3);
			servicioService.registrar(servicio4);
			servicioService.registrar(servicio5);
			servicioService.registrar(servicio6);
			
			IncidenteDTO incidente1= new IncidenteDTO(null,"Ruidos en la madrugada","Ruido entre 11.00 pm a 06:00 am",(long)1);
			IncidenteDTO incidente2= new IncidenteDTO(null,"Fiesta sin Autorización","Realizó una fiesta sin autorización de la junta de propietarios",(long)1);
			IncidenteDTO incidente3= new IncidenteDTO(null,"Suiedad de Mascotas","La mascota ensucio las áreas comunes y no se limpio",(long)1);
			IncidenteDTO incidente4= new IncidenteDTO(null,"Daños a las áreas comunes","Destrucción o deterioro de áreas comunes",(long)1);
			IncidenteDTO incidente5= new IncidenteDTO(null,"Pelea","Pelea ocurrida al interior del edificio",(long)1);
			
			incidenteService.registrar(incidente1);
			incidenteService.registrar(incidente2);
			incidenteService.registrar(incidente3);
			incidenteService.registrar(incidente4);
			incidenteService.registrar(incidente5);
			
			ProgramacionPagosDTO pp=new ProgramacionPagosDTO();
			pp.setMonth(5);
			pp.setYear(2022);
			programacionPagosService.registrarPagos(pp);
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