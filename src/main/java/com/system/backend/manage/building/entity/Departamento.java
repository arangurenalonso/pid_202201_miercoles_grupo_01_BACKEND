package com.system.backend.manage.building.entity;

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

	@Column(name = "depnumero", nullable = false)
	private String depnumero;

	@Column(name = "deptelef", nullable = false)
	private String deptelef;

	private Boolean estado;

	@OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","propietario","estado"})
	private Set<PropietarioDepartamento> propietarioDepartamentos = new HashSet<>();
	
	@ManyToOne(targetEntity = Persona.class, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","nombre","apellido"})
    private Persona personaRegistro;
	
	
}