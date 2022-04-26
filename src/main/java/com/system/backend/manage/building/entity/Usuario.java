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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties("password")
@Table(name = "usuarios")     
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String password;
	

	private String foto;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
	private Persona  persona;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDateDisplay;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
	
	 @JsonProperty
	private boolean isActive;
	
	 @JsonProperty
    private boolean isNotLocked;
	/********************************************
	 * fetch = FetchType.EAGER When I fetch the user or when I load the user; at the
	 * same time I load all of their roles in the database So there's no time that
	 * I'm going to load a user and I'm not going to load the role that's why I set
	 * this to eager; Because I want load all of the roles whenever I load the
	 * user
	 ******************************************/
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","role"})
	private Set<Permiso> permiso = new HashSet<>();

}