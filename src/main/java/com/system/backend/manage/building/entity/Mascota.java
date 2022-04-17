package com.system.backend.manage.building.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Table(name = "mascotas")
public class Mascota {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Definir el tipo de Mascota!!!!!")
	@Column(nullable = false,name = "tipo_mascota")
	private String tipoMascota;	
	
	@NotEmpty(message = "No puede estar vacio!!!!!")
	@Column(nullable = false)
	private String nombre;	
	
	@NotEmpty(message = "Raza es un campo obligatorio !!!!!")
	@Column(nullable = false)
	private String raza;
	
	@ManyToOne
	@JoinColumn(name="propietario_id")
	//@JsonIgnore
	private Propietario propietario;
}
