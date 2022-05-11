package com.system.backend.manage.building.dto.salida;



import com.system.backend.manage.building.entity.Persona;
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
public class PersonaDTOSalida {

	private Long id;

	private String nombre;

	private String apellido;

	private String dni;

	public PersonaDTOSalida(Persona persona) {
		this.id = persona.getId();
		this.nombre = persona.getNombre();
		this.apellido = persona.getApellido();
		this.dni = persona.getDni();
	}

}
