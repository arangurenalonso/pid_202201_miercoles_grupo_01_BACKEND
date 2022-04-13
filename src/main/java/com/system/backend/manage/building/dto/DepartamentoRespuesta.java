package com.system.backend.manage.building.dto;

import java.util.List;

public class DepartamentoRespuesta {
	
	private List<DepartamentoDTO>contenido;
	private int numeroDePagina;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;
	public List<DepartamentoDTO> getContenido() {
		return contenido;
	}
	public void setContenido(List<DepartamentoDTO> contenido) {
		this.contenido = contenido;
	}
	public int getNumeroDePagina() {
		return numeroDePagina;
	}
	public void setNumeroDePagina(int numeroDePagina) {
		this.numeroDePagina = numeroDePagina;
	}
	public int getMedidaPagina() {
		return medidaPagina;
	}
	public void setMedidaPagina(int medidaPagina) {
		this.medidaPagina = medidaPagina;
	}
	public long getTotalElementos() {
		return totalElementos;
	}
	public void setTotalElementos(long totalElementos) {
		this.totalElementos = totalElementos;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	public boolean isUltima() {
		return ultima;
	}
	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}
	public DepartamentoRespuesta() {
		super();
	}
	

}
