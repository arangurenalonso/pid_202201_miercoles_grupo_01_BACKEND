package com.system.backend.manage.building.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
@Table(name = "programacion_pagos")
public class ProgramacionPagos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "departamento_id", referencedColumnName = "id")
	@JsonIncludeProperties(value = {"id","depnumero","deptelef","piso"})
	private Departamento departamento;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "servicio_id", referencedColumnName = "id")
	@JsonIncludeProperties(value = {"id","nombre","descripcion","costo","isActive"})
	private Servicio servicio;
	
	
	@ManyToOne
	@JoinColumn(name="month_year_id")
	@JsonIncludeProperties(value = {"id","month","year"})
	private MonthYear monthYear;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at",updatable = false)
	private Date createAt;

	@Column(name = "estado")
	private int estado;//1: pendiente, 2: cancelado
	
	
}
