package com.system.backend.manage.building.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="servicios",uniqueConstraints = {@UniqueConstraint(columnNames = {"cod_servicio"})})
public class Servicios {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idservicio" )
	private Long id;
	
	
	@Column(nullable = false, name = "cod_servicio" )
	private String codigo_serv;
	
	
	
	@NotEmpty(message = "No debe estar en blanco")
	@Size(min=3,max=30,message = "El tamaño debe ser entre 3 y 30 ")
	@Column(nullable = false, name = "nom_servicio" )
	private String nombre_serv;
	
	@Column( name = "desc_servicio" )
	private String Descripcion_serv;
	
	@Column(name="nomempresa_servicio")
	private String NomEmpresa_servicio;
	

	@Size(min=1,max=10,message = "El tamaño debe ser entre 1 y 10 ")
	@Column( name="serv_costo")
	private String Costo_serv;
	
	
	
	@Column(name="fecha_registro",updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Column(name="estado")
	private Boolean estado;
	
	
	public Servicios (Long id,
			@NotEmpty(message = "No puede estar vacio!!!!!") @Size(min =5, max = 5, message = "El tamaño debe ser de 5 caracteres") String codigo,
			@NotEmpty(message = "No puede estar vacio!!!!!") @Size(min = 2, max = 20, message = "El tamaño debe ser mayor a 2 y menor a 20 caracteres ") String nombre_servicio,
			@NotEmpty(message = "No puede estar vacio!!!!!") @Size(min = 0, max = 200, message = "El tamaño debe ser menor a 200 caracteres") String descripcion_servicio,
			@NotEmpty(message = "No puede estar vacio!!!!!") @Size(min = 10, max = 10, message = "El tamaño debe ser de 5 caracteres") String nomempresa_servicio,
			@NotEmpty(message = "No puede estar vacio!!!!!") String Costo_serv, Boolean estado,
			@NotNull(message = "No puede estar vacio") Date createAt) {
		super();
		this.id = id;
		this.codigo_serv = codigo;
		this.nombre_serv = nombre_servicio;
		this.Descripcion_serv =descripcion_servicio;
		this.NomEmpresa_servicio = nomempresa_servicio ;
		this.Costo_serv = Costo_serv;
		this.createAt = createAt;
		this.estado= estado;
	}

	private static final long serialVersionUID = 1L;
	

}
