package com.system.backend.manage.building.jsonignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system.backend.manage.building.entity.Departamento;

@JsonIgnoreProperties({"propietarioDepartamentos","personaRegistro"})
public class DepartamentoIgnoreProperties extends Departamento{

}
