package com.system.backend.manage.building.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
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
	
	private Boolean enabled;
	
	/**********************************************************************************************
	 * TemporalType: Indico cual va a ser la transformacion o el tipo equivalente en la Base de dato
	 * *******************************************************************************************/
	
	@NotNull(message = "No puede estar vacio")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	


	private static final long serialVersionUID = 1L;
}
