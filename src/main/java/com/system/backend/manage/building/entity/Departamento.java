package com.system.backend.manage.building.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Departamentos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }),
		@UniqueConstraint(columnNames = { "depnumero" }) })


public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "depnumero", nullable = false)
	private String depnumero;

	@Column(name = "deptelef", nullable = false)
	private String deptelef;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepnumero() {
		return depnumero;
	}

	public void setDepnumero(String depnumero) {
		this.depnumero = depnumero;
	}

	public String getDeptelef() {
		return deptelef;
	}

	public void setDeptelef(String deptelef) {
		this.deptelef = deptelef;
	}

	
	public Departamento() {
		super();
	}

	public Departamento(long id, String depnumero, String deptelef) {
		super();
		this.id = id;
		this.depnumero = depnumero;
		this.deptelef = deptelef;
	
	}

}