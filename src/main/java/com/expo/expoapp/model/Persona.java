package com.expo.expoapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Persona {
	@Id
	@Column(length = 10, nullable = false, unique = true)
	private String matricula;
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(length = 25, nullable = false)
	private String nombre;
	@Column(length = 25, nullable = false)
	private String apellido;
	@Column(nullable = false)
	@Lob
	private byte[] foto;
}
