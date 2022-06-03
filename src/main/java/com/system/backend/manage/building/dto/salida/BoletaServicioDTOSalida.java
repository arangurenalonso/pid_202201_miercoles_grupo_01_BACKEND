package com.system.backend.manage.building.dto.salida;

import java.util.Date;


import com.system.backend.manage.building.entity.BoletaServicio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoletaServicioDTOSalida {
	private Long id;
	private MonthYearDTOSalida monthYear;
	
	private DepartamentoDTOSalida departamento;
	
	private ServicioDTOSalida servicio;
	
	private Date createAt;

	private int estado;//1: pendiente, 2: cancelado	
	
	private double costo;
	
	private Date fechaVenciemintoPago;

	public BoletaServicioDTOSalida(BoletaServicio pp) {
		super();
		this.id = pp.getId();
		this.departamento =new DepartamentoDTOSalida( pp.getDepartamento());
		this.servicio=new ServicioDTOSalida(pp.getServicio());
		this.monthYear=new MonthYearDTOSalida(pp.getMonthYear());
		this.createAt=pp.getCreateAt();
		this.estado=pp.getEstado();
		this.fechaVenciemintoPago=pp.getFechaVenciemintoPago();
		this.costo=pp.getCosto();
	}

}
