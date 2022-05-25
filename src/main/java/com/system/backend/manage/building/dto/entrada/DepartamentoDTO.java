package com.system.backend.manage.building.dto.entrada;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;


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
public class DepartamentoDTO {
	
	
	private Long id;
	
	@NotBlank(message = "Campo 'Número de Departamento' es obligatorio",groups = {CreateDepartamento.class,UpdatedDepartamento.class})
    @Pattern(regexp = "^-?[1-7][0-9]{2}+$",message="Debe contener 3 cifras y deben ser numeros enteros",groups = {CreateDepartamento.class})
	private String depnumero;
	
	@NotBlank(message = "Campo 'Número de Teléfono' es obligatorio",groups = {UpdatedDepartamento.class})
    @Pattern(regexp = "^-?[0-9]{7}+$",message="Debe contener 7 cifras y deben ser numeros enteros",groups = {UpdatedDepartamento.class})
	private String deptelef;
    
    @Min(
            value = 1,
            message = "El piso pertenece como nínimo al piso 1",
            groups ={CreateDepartamento.class}
    )
    @Max(
            value = 7,
            message = "El Departamento no puede estar ubicado por encima del piso 7",
            groups ={CreateDepartamento.class}
    )
    private int piso;
    
    
    @Min(
            value = 1,
            message = "El Aforo mínimo es de 1 persona",
            groups ={CreateDepartamento.class}
    )
    @Max(
            value = 7,
            message = "El Aforo máximo es de 7 personas",
            groups ={CreateDepartamento.class}
    )
    private int aforo;
    
	private Boolean estado;
	
	@NotNull(message = "El campo IdPersonaRegistro es obligatorio",groups = {CreateDepartamento.class})
	private Long idPersonaRegistro;
	
	public interface CreateDepartamento {
	}

	public interface UpdatedDepartamento {
	}

}