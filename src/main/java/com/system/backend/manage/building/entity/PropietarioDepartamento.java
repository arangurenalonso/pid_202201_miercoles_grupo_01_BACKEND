package com.system.backend.manage.building.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "propietario_departamentos",uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "propietario_id", "departamento_id" }) })
public class PropietarioDepartamento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="propietario_id")
	//@JsonIgnore
	@JsonIncludeProperties(value = {"id","persona"})
	private Propietario propietario;
	
	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn(name="departamento_id")
	@JsonIncludeProperties(value = {"id","depnumero"})
	private Departamento departamento;
	
	@Column(name="estado")
	private Boolean estado;
}
