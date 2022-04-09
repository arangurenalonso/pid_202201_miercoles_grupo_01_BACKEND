package com.system.backend.manage.building.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermisoId implements Serializable {

	
	private Long usuarioId;
	private Long roleId;
	
	
	private static final long serialVersionUID = 1L;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PermisoId))
			return false;
		
		PermisoId permisoId=(PermisoId) obj;
		return Objects.equals(getUsuarioId(), permisoId.getUsuarioId()) &&
				Objects.equals(getRoleId(), permisoId.getRoleId());
		
	}


	@Override
	public int hashCode() {
		return Objects.hash(getUsuarioId(), getRoleId());
	}
	
}
