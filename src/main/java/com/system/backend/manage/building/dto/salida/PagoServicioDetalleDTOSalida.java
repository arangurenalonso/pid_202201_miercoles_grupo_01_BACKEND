package com.system.backend.manage.building.dto.salida;

import java.util.Date;


import com.system.backend.manage.building.entity.BoletaServicio;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.PagoServicio;
import com.system.backend.manage.building.entity.PagoServicioDetalle;
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
public class PagoServicioDetalleDTOSalida {
	private Long id;
	private BoletaServicioDTOSalida boletaServicio;
	
	public PagoServicioDetalleDTOSalida(PagoServicioDetalle ps) {
		super();
		this.id = ps.getId();
		this.boletaServicio=new BoletaServicioDTOSalida(ps.getBoletaServicio());
	}

}
