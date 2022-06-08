package com.system.backend.manage.building.dto.salida;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.system.backend.manage.building.entity.BoletaServicio;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.PagoServicio;
import com.system.backend.manage.building.entity.Persona;

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
public class PagoServicioDTOSalida {
	private Long id;
	private double montoTotal;
	
	private Date createAt;
	private PersonaDTOSalida personaRegistro;
	
	private DepartamentoDTOSalida departamento;
	private List<PagoServicioDetalleDTOSalida> pagoServicioDetalle;
	
	public PagoServicioDTOSalida(PagoServicio ps) {
		super();
		this.id = ps.getId();
		this.montoTotal=ps.getMontoTotal();
		this.createAt=ps.getCreateAt();	
		this.personaRegistro=new PersonaDTOSalida(ps.getPersonaRegistro());	
		this.departamento =new DepartamentoDTOSalida( ps.getDepartamento());
		this.pagoServicioDetalle=ps.getPagoServicioDetalle().stream().map(obj->{
															return new PagoServicioDetalleDTOSalida(obj);
															}).collect(Collectors.toList());
	}

}
