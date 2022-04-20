package com.system.backend.manage.building.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "personas")
public class Persona implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio!!!!!")
	@Size(min=2,max=20,message = "El tamaño debe estar entre 4 y 12 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty(message = "No puede estar vacio!!!!!")
	@Size(min=2,max=20,message = "El tamaño debe estar entre 4 y 12 caracteres")
	private String apellido;
	
	@NotEmpty(message = "No puede estar vacio!!!!!")
	@Column(nullable = false, unique=true)
	private String dni;
	
	@Column(name="estado")
	private Boolean estado;
	


	/**********************************************************************************************
	 * TemporalType: Indico cual va a ser la transformacion o el tipo equivalente en la Base de dato
	 * *******************************************************************************************/
	
	@NotNull(message = "No puede estar vacio")
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToOne(mappedBy = "persona")
	@JsonManagedReference
	private Usuario usuario;

	public Persona(Long id,
			@NotEmpty(message = "No puede estar vacio!!!!!") @Size(min = 2, max = 20, message = "El tamaño debe estar entre 4 y 12 caracteres") String nombre,
			@NotEmpty(message = "No puede estar vacio!!!!!") @Size(min = 2, max = 20, message = "El tamaño debe estar entre 4 y 12 caracteres") String apellido,
			@NotEmpty(message = "No puede estar vacio!!!!!") String dni, Boolean estado,
			@NotNull(message = "No puede estar vacio") Date createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.estado = estado;
		this.createAt = createAt;
	}

	private static final long serialVersionUID = 1L;
	
	
	
	
	
	
	
	
	
}
