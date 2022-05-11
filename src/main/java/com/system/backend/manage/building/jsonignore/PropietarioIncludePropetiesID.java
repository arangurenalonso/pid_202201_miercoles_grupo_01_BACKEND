package com.system.backend.manage.building.jsonignore;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.system.backend.manage.building.entity.Propietario;

@JsonIncludeProperties({"id"})
public class PropietarioIncludePropetiesID extends Propietario{

}
