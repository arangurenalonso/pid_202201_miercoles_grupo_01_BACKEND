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
	
	@NotBlank(message = "Numero Departamento Requerido")
    @Pattern(regexp = "^-?[0-9]{3}+$",message="Debe contener 3 cifras y deben ser numeros enteros")
	private String depnumero;
    @NotBlank(message = "Numero de Telefono Requerido")
    @Pattern(regexp = "^-?[0-9]{7}+$",message="Debe contener 7 cifras y deben ser numeros enteros")
	private String deptelef;
    @NotNull(message = "Numero Piso no puede estar vacio")
    @Min(1)
    @Max(9)
    private int piso;
    @NotNull(message = "No puede estar vacio")
    @Min(1)
    @Max(7)
    private int aforo;
	private Boolean estado;
	
	
	
}