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
@Table(name = "propietarios")
public class Propietario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="birthday_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdayDate;
	
	
	@Column(name="cellphone", nullable = false)
	private String numeroCelular;
	
	public Propietario(Long id, Date birthdayDate, 
			String numeroCelular, Persona persona) {
		super();
		this.id = id;
		this.birthdayDate = birthdayDate;
		this.numeroCelular = numeroCelular;
		this.persona = persona;
	}
	
	/*******************************************************
	 * Relaciones con otras tablas
	 ****************************************************/
	@OneToMany(mappedBy = "propietario",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set <Mascota> mascotas = new HashSet<>();//new ArrayList<> ();

	@OneToMany(mappedBy = "propietario",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set <Familiar> familiares = new HashSet<>();
	
	@OneToMany(mappedBy = "propietario",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","departamento","estado"})
	private Set<PropietarioDepartamento> propietarioDepartamentos = new HashSet<>();
	
	@OneToMany(mappedBy = "propietario",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","fechaHoraLlegada","fechaHoraSalida","motivoVisita"})
	private Set<Visita> visitas = new HashSet<>();

	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	private Persona persona;
	

	
	
}
