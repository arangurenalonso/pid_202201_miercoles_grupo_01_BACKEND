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
@Table(name = "visitas")
public class Visita {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	@Column(name = "fechaHoraLlegada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraLlegada;
	
	@Column(name = "fechaHoraSalida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraSalida;
	
	@Column(name = "motivo_visita", nullable = false)
	private String motivoVisita;
	
	@Column(name = "observacion_salida")
	private String observacionSalida;
	
	@Column(name = "create_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Column(name = "estado", nullable = false)
	private int estado;//1: Registrado ; 2: Terminado; 3: Cancelado
	/********************************************************
	 * Relaciones con tablas
	 *********************************************************/
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "propietario_id", referencedColumnName = "id")
	@JsonIncludeProperties(value = {"id","numeroCelular","persona"})
	private Propietario propietario;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "departamento_id", referencedColumnName = "id")
	@JsonIncludeProperties(value = {"id","depnumero","deptelef","piso"})
	private Departamento departamento;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "visitante_id", referencedColumnName = "id")
	@JsonIncludeProperties(value = {"id","persona"})
	private Visitante visitante;

	@ManyToOne(targetEntity = Persona.class, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","nombre","apellido"})
    private Persona personaRegistro;
	
	
}
