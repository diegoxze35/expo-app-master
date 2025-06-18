package com.expo.expoapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class ExpoUser {
	@Id
	@Column(length = 10, nullable = false)
	private String matriculate;
	@Column(length = 50, unique = true)
	private String email;
	@Column()
	private String password;
	@Column(length = 25, nullable = false)
	private String name;
	@Column(length = 25, nullable = false)
	private String surname;
	@Column()
	private String photoUrl;
}
