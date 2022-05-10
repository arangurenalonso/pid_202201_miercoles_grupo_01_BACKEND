package com.system.backend.manage.building.service;


import java.util.List;

import com.system.backend.manage.building.dto.entrada.DepartamentoDTO;
import com.system.backend.manage.building.dto.salida.PaginacionRespuesta;
import com.system.backend.manage.building.entity.Departamento;


public interface DepartamentoService {

	public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO);

	public PaginacionRespuesta obtenertodoslosDepartamento(int numeroDePagina,int medidaDePagina,String ordenarPor,String SortDir);

	public Departamento obtenerDepartamentosPorId(long id) ;

	public DepartamentoDTO actualizarDepartamento(DepartamentoDTO departamentoDTO, long id);
	
	public DepartamentoDTO eliminarDepartamento(long id);

	public List<Departamento> listaDepartamentoDisponibles();
	
	public List<Departamento> listaTodosDepartamnetos();

	public Departamento changeActive(long id);
}
