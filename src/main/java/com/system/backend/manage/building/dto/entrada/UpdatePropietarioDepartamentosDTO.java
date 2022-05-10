package com.system.backend.manage.building.dto.entrada;

import java.util.ArrayList;
import java.util.List;

import com.system.backend.manage.building.jsonignore.DepartamentoIgnoreProperties;

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
public class UpdatePropietarioDepartamentosDTO {

	
	
	//@NotEmpty(message = "Debe contener por lo menos 1 departamento")
	private List<DepartamentoIgnoreProperties> departamentosCrear=new ArrayList<>();
	private List<DepartamentoIgnoreProperties> departamentosActivar=new ArrayList<>();
	private List<DepartamentoIgnoreProperties> departamentosDesactivar=new ArrayList<>();
}
