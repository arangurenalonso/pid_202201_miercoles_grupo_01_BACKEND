package com.system.backend.manage.building.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.dto.entrada.ProgramacionPagosDTO;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.MonthYear;
import com.system.backend.manage.building.entity.ProgramacionPagos;
import com.system.backend.manage.building.entity.Servicio;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.repository.IMounthYearRepository;
import com.system.backend.manage.building.repository.IProgramacionPagosRepository;
import com.system.backend.manage.building.service.DepartamentoService;
import com.system.backend.manage.building.service.ProgramacionPagosService;
import com.system.backend.manage.building.service.ServicioService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProgramacionPagoServiceImpl implements ProgramacionPagosService {

	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private ServicioService servcioService;
	@Autowired
	private IProgramacionPagosRepository programacionPagosRepository;
	@Autowired
	private IMounthYearRepository mounthYearRepository;

	@Override
	@Transactional
	public List<ProgramacionPagos> registrarPagos(ProgramacionPagosDTO programacionPagosDTO) {
		validateNewMonthYear(programacionPagosDTO.getMonth(), programacionPagosDTO.getYear());
		List<Servicio> servicios = servcioService.listadoAllServicio();
		List<Departamento> departamentos = departamentoService.listaTodosDepartamnetos();

		List<ProgramacionPagos> listaPagosProgramados = new ArrayList<>();

		MonthYear my = new MonthYear(null, programacionPagosDTO.getMonth(), programacionPagosDTO.getYear());
		MonthYear myCreated = mounthYearRepository.save(my);
		servicios.stream().forEach(serv -> {
			departamentos.stream().forEach(dep -> {
				ProgramacionPagos pp = new ProgramacionPagos(null, dep, serv, myCreated, new Date(), 1);
				listaPagosProgramados.add(pp);
			});
		});

		return programacionPagosRepository.saveAll(listaPagosProgramados);
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
	public List<Servicio> listaServiciosPendientePagosPorMonthYearDepartamento(ProgramacionPagosDTO programacionPagosDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
