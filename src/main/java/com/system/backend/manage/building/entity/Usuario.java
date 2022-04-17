package com.system.backend.manage.building.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "usuarios")     
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@NotEmpty(message = "No puede estar vacio!!!!!")
	@Email(message = "Debe ingresar un correo válido")
	@Column(nullable = false, unique = true)
	private String email;
	
	private String password;
	
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
	//@JsonBackReference
	private Persona  persona=new Persona();
	
	@Temporal(TemporalType.DATE)
	private Date lastLoginDateDisplay;
	
	@Temporal(TemporalType.DATE)
    private Date lastLoginDate;
	private boolean isActive;
    private boolean isNotLocked;
	/********************************************
	 * fetch = FetchType.EAGER When I fetch the user or when I load the user; at the
	 * same time I load all of their roles in the database So there's no time that
	 * I'm going to load a user and I'm not going to load the role that's why I set
	 * this to eager; Because I want lo load all of the roles whenever I load the
	 * user
	 ******************************************/
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Permiso> permiso = new ArrayList<>();

}