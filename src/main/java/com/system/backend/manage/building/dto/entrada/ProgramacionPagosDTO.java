package com.system.backend.manage.building.dto.entrada;

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
public class ProgramacionPagosDTO {
	private Long id;

	private int month;

	private int year;

	private Long idDepartamento;
}
