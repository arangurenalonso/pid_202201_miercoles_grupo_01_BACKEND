package com.system.backend.manage.building.dto.entrada;

import java.util.ArrayList;
import java.util.List;

import com.system.backend.manage.building.dto.salida.BoletaServicioDTOSalida;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.jsonignore.BoletaServicioIgnoreProperties;

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
public class CancelarPagosDTO {

	private Long idPersonaRegistro;
	private Departamento departamento;
	private List<BoletaServicioDTOSalida> pagosACancelar=new ArrayList<>();
}
