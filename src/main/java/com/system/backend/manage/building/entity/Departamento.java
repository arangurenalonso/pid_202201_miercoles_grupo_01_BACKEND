package com.system.backend.manage.building.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Departamento", uniqueConstraints = { @UniqueConstraint(columnNames = { "idDepartamento" }) })
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDepartamento;

	@Column(name = "depmetrosConst", nullable = false)
	private String depmetrosConst;

	@Column(name = "depniveles", nullable = false)
	private String depniveles;

	@Column(name = "depcuartos", nullable = false)
	private String depcuartos;

	@Column(name = "depbaños", nullable = false)
	private String depbaños;

	@Column(name = "depnumero", nullable = false)
	private String depnumero;

	@Column(name = "depcochera", nullable = false)
	private String depcochera;

	@Column(name = "idpropetario", nullable = false)
	private int idpropetario;

	public long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getDepmetrosConst() {
		return depmetrosConst;
	}

	public void setDepmetrosConst(String depmetrosConst) {
		this.depmetrosConst = depmetrosConst;
	}

	public String getDepniveles() {
		return depniveles;
	}

	public void setDepniveles(String depniveles) {
		this.depniveles = depniveles;
	}

	public String getDepcuartos() {
		return depcuartos;
	}

	public void setDepcuartos(String depcuartos) {
		this.depcuartos = depcuartos;
	}

	public String getDepbaños() {
		return depbaños;
	}

	public void setDepbaños(String depbaños) {
		this.depbaños = depbaños;
	}

	public String getDepnumero() {
		return depnumero;
	}

	public void setDepnumero(String depnumero) {
		this.depnumero = depnumero;
	}

	public String getDepcochera() {
		return depcochera;
	}

	public void setDepcochera(String depcochera) {
		this.depcochera = depcochera;
	}

	public int getIdpropetario() {
		return idpropetario;
	}

	public void setIdpropetario(int idpropetario) {
		this.idpropetario = idpropetario;
	}

	public Departamento() {
		super();
	}

	public Departamento(long idDepartamento, String depmetrosConst, String depniveles, String depcuartos,
			String depbaños, String depnumero, String depcochera, int idpropetario) {
		super();
		this.idDepartamento = idDepartamento;
		this.depmetrosConst = depmetrosConst;
		this.depniveles = depniveles;
		this.depcuartos = depcuartos;
		this.depbaños = depbaños;
		this.depnumero = depnumero;
		this.depcochera = depcochera;
		this.idpropetario = idpropetario;
	}

}