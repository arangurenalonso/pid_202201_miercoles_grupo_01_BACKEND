package com.system.backend.manage.building.dto.salida;

import com.system.backend.manage.building.entity.MonthYear;
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
public class MonthYearDTOSalida {
	private Long id;
	
	private int month;
	
	private int year;
	

	public MonthYearDTOSalida(MonthYear my) {
		super();
		this.id = my.getId();
		this.month=my.getMonth();
		this.year=my.getYear();
	}

}
