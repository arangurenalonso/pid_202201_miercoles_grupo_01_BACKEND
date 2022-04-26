package com.system.backend.manage.building.constant;

public class UserImplConstant {
	public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String NO_USER_FOUND_BY_USERNAME = "No user found by username: ";
    public static final String FOUND_USER_BY_USERNAME = "Returning found user by username: ";
    public static final String NO_USER_FOUND_BY_EMAIL = "No user found for email: ";
    public static final String RESOURCE_NOT_FOUND_EXCEPTION="Recurso no encontrado";
    public static final String DNI_EXISTENTE="DNI YA SE ENCUENTRA REGISTRADO";
    
    public static String DEPARTAMENTO_EXISTENTE(String numDepartamento) {
    	return "Departamento N°"+numDepartamento+ " ya creado";
    }
}
