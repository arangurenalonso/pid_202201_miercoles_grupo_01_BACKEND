package com.system.backend.manage.building.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.BoletaServicioDTO;
import com.system.backend.manage.building.dto.salida.BoletaServicioDTOSalida;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.MonthYear;
import com.system.backend.manage.building.entity.BoletaServicio;
import com.system.backend.manage.building.entity.Servicio;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IMounthYearRepository;
import com.system.backend.manage.building.repository.IBoletaServicioRepository;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.PersonaService;
import com.system.backend.manage.building.service.BoletaServicioService;
import com.system.backend.manage.building.service.ServicioService;
import com.system.backend.manage.building.utils.Calendario;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoletaServicioServiceImpl implements BoletaServicioService {

	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private ServicioService servcioService;
	@Autowired
	private IBoletaServicioRepository boletaServicioRepository;
	@Autowired
	private IMounthYearRepository mounthYearRepository;
	@Autowired
	private PersonaService personaServ;
	
	@Override
	@Transactional
	public List<BoletaServicio> registrarPagos(BoletaServicioDTO programacionPagosDTO) {
		validateNewMonthYear(programacionPagosDTO.getMonth(), programacionPagosDTO.getYear());
		Date lastDayMonthYear=Calendario.calcularUltimoDiaMes(programacionPagosDTO.getMonth(), programacionPagosDTO.getYear()) ;
		List<Servicio> servicios = servcioService.listadoAllServicio();
		List<Departamento> departamentos = departamentoService.listaTodosDepartamnetos();
		List<BoletaServicio> listaPagosProgramados = new ArrayList<>();

		MonthYear my = new MonthYear(null, programacionPagosDTO.getMonth(), programacionPagosDTO.getYear());
		MonthYear myCreated = mounthYearRepository.save(my);
		servicios.stream().forEach(serv -> {
			departamentos.stream().forEach(dep -> {
				BoletaServicio pp = new BoletaServicio(null,serv.getCosto(),lastDayMonthYear,new Date(),1,myCreated,dep, serv);
				listaPagosProgramados.add(pp);
			});
		});

		return boletaServicioRepository.saveAll(listaPagosProgramados);
	}


	private boolean validateNewMonthYear(int month,int year) {
		MonthYear my = mounthYearRepository.buscarMonthYear(month,year);

		if (my != null) {
			throw new CustomAppException("Ya se han registrado los servicios para el mes "+month+" y el año "+year, 409,
					"Servicios Programados" , "ProgramacionPagosExistException",
					HttpStatus.CONFLICT);
		}
		return true;
	}
	@Override
	public List<BoletaServicioDTOSalida> listarPagosPorPagarPorDepartamento(long  idDepartamento) {
		Departamento dep =departamentoService.obtenerDepartamentosPorId(idDepartamento);
		List<BoletaServicio> ppPendientes=boletaServicioRepository.listarPagosPorPagarPorDepartamento(dep.getId());
		
		 List<BoletaServicioDTOSalida> listaPagosPendientes = ppPendientes.stream()
                 .map(x->{
                	 return new BoletaServicioDTOSalida(x);
                 })
                 .collect(Collectors.toList());
		
		return listaPagosPendientes;
	}



	
	public BoletaServicio buscarBoletaServicio(long id) {
		BoletaServicio bs=boletaServicioRepository.findById(id).orElseThrow(() -> new CustomAppException(
				"El id '" + id + "' no existe en la Base de datos", 400,
				UserImplConstant.RESOURCE_NOT_FOUND_EXCEPTION, "ResourceNotFoundException", HttpStatus.BAD_REQUEST));		
		return bs;
	}

}
