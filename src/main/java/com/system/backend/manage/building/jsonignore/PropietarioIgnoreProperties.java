package com.system.backend.manage.building.jsonignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system.backend.manage.building.entity.Propietario;

@JsonIgnoreProperties({"mascotas","familiares","propietarioDepartamentos"})
public class PropietarioIgnoreProperties extends Propietario{

}
