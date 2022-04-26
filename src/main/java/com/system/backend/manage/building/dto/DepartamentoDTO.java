package com.system.backend.manage.building.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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
	
	@NotBlank(message = "Campo 'Número de Departamento' es obligatorio")
    @Pattern(regexp = "^-?[1-7][0-9]{2}+$",message="Debe contener 3 cifras y deben ser numeros enteros")
	private String depnumero;
	
	@NotBlank(message = "Campo 'Número de Teléfono' es obligatorio")
    @Pattern(regexp = "^-?[0-9]{7}+$",message="Debe contener 7 cifras y deben ser numeros enteros")
	private String deptelef;
    
    @NotNull(message = "Campo 'Piso' es obligatorio")
    @Min(1)
    @Max(9)
    private int piso;
    
    @NotNull(message = "Campo 'Aforo' es obligatorio")
    @Min(1)
    @Max(7)
    private int aforo;
    
	private Boolean estado;
	
	
	private Long idPersonaRegistro;
}