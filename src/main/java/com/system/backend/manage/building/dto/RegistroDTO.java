package com.system.backend.manage.building.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

public class RegistroDTO {
     
	@Size(min=1,max=50,message="Ingresar Nombre")
	@Pattern(regexp = "[a-z][A-Z]")
	private String nombre;
	@Size(min=1,max=50,message="Ingresar Username")
	private String username;
	@Email
	private String email;
	@Size(min=4)
	private String password;



}
