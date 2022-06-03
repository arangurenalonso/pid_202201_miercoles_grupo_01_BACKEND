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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "pago_servicio")
public class PagoServicio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column( name="monto_total",nullable = false)
	private double montoTotal;
	
	@Column(name = "create_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@ManyToOne(targetEntity = Persona.class, fetch = FetchType.EAGER)
	@JsonIncludeProperties(value = {"id","nombre","apellido"})
    private Persona personaRegistro;

	
	@ManyToOne
	@JoinColumn(name="departamento_id")
	@JsonIgnore
	private Departamento departamento;
	
	
	public PagoServicio(Long id, double montoTotal, Date createAt, Persona personaRegistro, Departamento departamento) {
		super();
		this.id = id;
		this.montoTotal = montoTotal;
		this.createAt = createAt;
		this.personaRegistro = personaRegistro;
		this.departamento = departamento;
	}


	@OneToMany(mappedBy = "pagoServicio",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set <PagoServicioDetalle> pagoServicioDetalle = new HashSet<>();
	

	
	
}
