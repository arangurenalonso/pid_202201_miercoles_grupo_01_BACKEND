package com.system.backend.manage.building.jsonignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system.backend.manage.building.entity.BoletaServicio;

@JsonIgnoreProperties({"pagoServicioDetalle","servicio","departamento","monthYear"})
public class BoletaServicioIgnoreProperties extends BoletaServicio{

	
}
