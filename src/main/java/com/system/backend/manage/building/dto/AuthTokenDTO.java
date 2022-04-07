    package com.system.backend.manage.building.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AuthTokenDTO {

	private String access_token;
	private String refresh_token;
	private String token_type = "Bearer";

	public AuthTokenDTO(String access_token,String refresh_token) {
		super();
		this.access_token = access_token;
		this.refresh_token = refresh_token;
	}

	

	

}
