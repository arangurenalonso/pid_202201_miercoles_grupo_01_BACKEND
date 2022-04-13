package com.system.backend.manage.building.dto;

public class DepartamentoDTO {
	
	private long id;
	private String depnumero;
	private String deptelef;
	
	
	
	public DepartamentoDTO(long id, String depnumero, String deptelef) {
		super();
		this.id = id;
		this.depnumero = depnumero;
		this.deptelef = deptelef;
	}
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
	public DepartamentoDTO() {
		super();
	}
}