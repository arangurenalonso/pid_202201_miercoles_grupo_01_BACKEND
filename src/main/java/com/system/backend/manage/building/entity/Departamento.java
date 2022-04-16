package com.system.backend.manage.building.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Departamentos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }),
		@UniqueConstraint(columnNames = { "depnumero" }) })
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "depnumero", nullable = false)
	private String depnumero;

	@Column(name = "deptelef", nullable = false)
	private String deptelef;

	private Boolean estado;

}