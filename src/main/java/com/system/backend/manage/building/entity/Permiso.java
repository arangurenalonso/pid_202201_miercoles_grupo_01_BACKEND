package com.system.backend.manage.building.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") 
@Table(name = "permisos")
public class Permiso {
	
	@EmbeddedId
	private PermisoId permisoId=new PermisoId();
	
	
	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name="usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne 
	@MapsId("roleId")
	@JoinColumn(name="role_id")
	//@JsonIgnore
	private Role role;
	

}
   

