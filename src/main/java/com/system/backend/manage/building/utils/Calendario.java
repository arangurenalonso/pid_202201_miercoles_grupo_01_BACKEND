package com.system.backend.manage.building.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Calendario {

	
	public static Date calcularUltimoDiaMes(int month, int year) {
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()); 
		return Date.from(lastDayOfMonth.atStartOfDay(defaultZoneId).toInstant());
		
	}
}
