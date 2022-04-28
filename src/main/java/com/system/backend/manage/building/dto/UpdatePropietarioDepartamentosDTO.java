package com.system.backend.manage.building.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.system.backend.manage.building.entity.Departamento;
import com.system.backend.manage.building.entity.PropietarioDepartamento;
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
