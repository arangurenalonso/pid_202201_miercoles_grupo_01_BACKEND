package com.system.backend.manage.building.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") 
@Table(name = "month_year")
public class MonthYear {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "month")
	private int month;
	
	@Column(name = "year")
	private int year;
	
	@OneToMany(mappedBy = "monthYear",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set <ProgramacionPagos> programacionPagos = new HashSet<>();

	public MonthYear(Long id, int month, int year) {
		super();
		this.id = id;
		this.month = month;
		this.year = year;
	}
	
}
