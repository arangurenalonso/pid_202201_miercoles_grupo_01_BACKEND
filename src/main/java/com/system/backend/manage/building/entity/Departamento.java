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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
@Table(name = "Departamentos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }),
		@UniqueConstraint(columnNames = { "depnumero" }) })
//@JsonIgnoreProperties({"propietarioDepartamento","personaRegistro"})
@JsonIgnoreProperties({"propietarioDepartamento"})
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "depnumero", nullable = false, unique = true)	
	private String depnumero;

	@Column(name = "deptelef", nullable = false)
	private String deptelef;
	
	@Column(name = "piso", nullable = false)
	private int piso;
	
	@Column(name = "aforo", nullable = false)
	private int aforo;
	
	@Column(name = "create_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	private Boolean estado;
	/*******************************************************
	 * Relaciones con otras tablas
	 ****************************************************/

	@OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","propietario","estado"})
	private Set<PropietarioDepartamento> propietarioDepartamentos = new HashSet<>();
	
	@OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","fechaHoraLlegada","fechaHoraSalida","motivoVisita"})
	private Set<Visita> visitas = new HashSet<>();
	
	@OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","month","year"})
	private Set<BoletaServicio> boletaServicio = new HashSet<>();
	
	@ManyToOne(targetEntity = Persona.class, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","nombre","apellido"})
    private Persona personaRegistro;

	@OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","fechaHoraLlegada","fechaHoraSalida","motivoVisita"})
	private Set <PagoServicio> pagoServicio = new HashSet<>();
	

	
}