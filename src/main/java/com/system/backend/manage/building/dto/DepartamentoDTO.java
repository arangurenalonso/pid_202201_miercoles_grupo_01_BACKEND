package com.system.backend.manage.building.dto;

import javax.persistence.Column;

public class DepartamentoDTO {

	private long idDepartamento;
	private String depmetrosConst;
	private String depniveles;
	private String depcuartos;
	private String depbaños;
	private String depnumero;
	private String depcochera;

	public DepartamentoDTO() {
		super();
	}

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

}
