package com.system.backend.manage.building.entity;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") 
@Table(name = "personas")
@JsonIgnoreProperties({ "children", "departamentosRegistrados", "mascotasRegistrados","servicioRegistro" ,"visitaRegistro"})
public class Persona implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellido", nullable = false)
	private String apellido;

	@Column(nullable = false, unique = true)
	private String dni;
	

	
	@Column(name = "estado")
	private Boolean estado;

	/**********************************************************************************************
	 * TemporalType: Indico cual va a ser la transformacion o el tipo equivalente en
	 * la Base de dato
	 *******************************************************************************************/

	@Column(name = "create_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	//@JsonManagedReference
	@OneToOne(mappedBy = "persona")
	@JsonIncludeProperties(value = { "id", "email", "foto", "lastLoginDateDisplay", "lastLoginDate", "isNotLocked",
			"isActive", "permiso" })
	private Usuario usuario;

	
	//@JsonManagedReference
	@ManyToOne(targetEntity = Persona.class, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "id", "nombre", "apellido" })
	private Persona personaRegistro;

	public Persona(Long id, String nombre, String apellido, String dni, Boolean estado, Date createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.estado = estado;
		this.createAt = createAt;
	}

	private static final long serialVersionUID = 1L;

	/*************************************************************************
	 * REGISTRO DE PERSONA QUE REGISTRA EN LAS DIFERENTES TABLAS
	 ***************************************************************************/

	@OneToMany(mappedBy = "personaRegistro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "id" })
	private Set<Persona> children = new HashSet<>();

	@OneToMany(mappedBy = "personaRegistro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "id" })
	private Set<Departamento> departamentosRegistrados = new HashSet<>();

	@OneToMany(mappedBy = "personaRegistro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "id" })
	private Set<Departamento> mascotasRegistrados = new HashSet<>();
	
	@OneToMany(mappedBy = "personaRegistro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "id" })
	private Set<Departamento> servicioRegistro = new HashSet<>();
	
	@OneToMany(mappedBy = "personaRegistro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "id" })
	private Set<Visita> visitaRegistro = new HashSet<>();
	
	@OneToMany(mappedBy = "personaRegistro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = { "id" })
	private Set<PagoServicio> pagoServicio = new HashSet<>();
	
	@OneToMany(mappedBy = "personaRegistro",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id"})
	private Set <HistorialIncidentes> historialIncidentes = new HashSet<>();

}
