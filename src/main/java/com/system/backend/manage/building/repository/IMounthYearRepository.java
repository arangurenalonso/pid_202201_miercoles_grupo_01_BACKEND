package com.system.backend.manage.building.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.backend.manage.building.entity.MonthYear;

public interface IMounthYearRepository extends JpaRepository<MonthYear, Long> {

	@Query("SELECT my FROM"
			+ " MonthYear my "
			+ " where "
			+ " my.month=:month and "
			+ " my.year=:year ")
	public abstract MonthYear buscarMonthYear( int month,int year);
}
