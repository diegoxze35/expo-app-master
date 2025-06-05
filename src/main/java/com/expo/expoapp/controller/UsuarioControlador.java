package com.expo.expoapp.controller;

import com.expo.expoapp.model.Persona;
import com.expo.expoapp.request.PersonaSolicitud;
import com.expo.expoapp.service.PersonaServicio;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

	private final PersonaServicio personaServicio;

	@Autowired
	public UsuarioControlador(PersonaServicio personaServicio) {
		this.personaServicio = personaServicio;
	}

	@GetMapping
	public ResponseEntity<List<Persona>> listarUsuarios() {
		return ResponseEntity.ok(personaServicio.listar());
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Persona> crearUsuario(
			@RequestPart("solicitud") PersonaSolicitud solicitud,
			@RequestPart("foto") MultipartFile foto
	) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				this.personaServicio.crearUsuario(solicitud, foto)
		);
	}

}
