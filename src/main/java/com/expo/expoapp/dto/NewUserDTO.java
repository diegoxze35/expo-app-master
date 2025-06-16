package com.expo.expoapp.dto;

import lombok.Data;

@Data
public abstract class NewUserDTO {
	private String fullName;
	private String email;
	private String matriculate;
}
