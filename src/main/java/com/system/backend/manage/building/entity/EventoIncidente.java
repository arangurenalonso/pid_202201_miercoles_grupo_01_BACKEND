package com.system.backend.manage.building.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "evento_incidente")
public class EventoIncidente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="departamento_id")
	@JsonIgnore
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="incidente_id")
	@JsonIgnore
	private Incidente incidente;
	
	
	@Column(name = "estado")
	private int estado;//1: Registrado; 2:atendido
	
	
	public EventoIncidente(Long id, Departamento departamento, Incidente incidente, int estado) {
		super();
		this.id = id;
		this.departamento = departamento;
		this.incidente = incidente;
		this.estado = estado;
	}


	
	
	@OneToMany(mappedBy = "eventoIncidente",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id"})
	private Set <HistorialIncidentes> historialIncidentes = new HashSet<>();
	
	
	
}
