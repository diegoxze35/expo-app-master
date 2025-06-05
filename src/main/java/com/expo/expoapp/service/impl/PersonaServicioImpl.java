package com.expo.expoapp.service.impl;

import com.expo.expoapp.model.Equipo;
import com.expo.expoapp.model.Estudiante;
import com.expo.expoapp.model.Persona;
import com.expo.expoapp.model.Profesor;
import com.expo.expoapp.repository.EquipoRepositorio;
import com.expo.expoapp.repository.UsuarioRepositorio;
import com.expo.expoapp.request.PersonaSolicitud;
import com.expo.expoapp.service.PersonaServicio;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonaServicioImpl implements PersonaServicio {

	private final PasswordEncoder encriptador;
	private final UsuarioRepositorio personaRepositorio;
	private final EquipoRepositorio equipoRepositorio;

	@Autowired
	public PersonaServicioImpl(
			PasswordEncoder encriptador,
			UsuarioRepositorio personaRepositorio,
			EquipoRepositorio equipoRepositorio
	) {
		this.encriptador = encriptador;
		this.personaRepositorio = personaRepositorio;
		this.equipoRepositorio = equipoRepositorio;
	}

	@Override
	public List<Persona> listar() {
		return personaRepositorio.findAll();
	}

	@Override
	public Persona crearUsuario(PersonaSolicitud persona, MultipartFile fotoPerfil) throws IOException {
		//Diego Alejandro Moreno Martinez
		String[] nombreCompleto = persona.nombre().split(" ");
		String nombres, apellidos;
		//[Diego, Alejandro, Moreno, Martinez]
		if (nombreCompleto.length == 4) {
			nombres = nombreCompleto[0] + " " + nombreCompleto[1];
			apellidos = nombreCompleto[2] + " " + nombreCompleto[3];
		} else {
			nombres = nombreCompleto[0];
			apellidos = nombreCompleto[1] + " " + nombreCompleto[2];
		}
		switch (persona.tipoUsuario()) {
			case Estudiante:
				Estudiante nuevoEstudiante = new Estudiante();
				nuevoEstudiante.setMatricula(persona.matricula());
				nuevoEstudiante.setEmail(persona.email());
				nuevoEstudiante.setPassword(encriptador.encode(persona.contrasenia()));
				nuevoEstudiante.setNombre(nombres);
				nuevoEstudiante.setApellido(apellidos);
				//Estudiante estudiante = (Estudiante) nuevoUsuario;
				nuevoEstudiante.setGrupo(persona.grupo());
				nuevoEstudiante.setSemestre(persona.semestre());
				nuevoEstudiante.setCarrera(persona.carrera());
				nuevoEstudiante.setFoto(fotoPerfil.getBytes());
				if (persona.nombreEquipo() != null) {
					Optional<Equipo> equipo = equipoRepositorio.findByNombreEquipo(persona.nombreEquipo());
					if (equipo.isPresent()) {
						nuevoEstudiante.setEquipo(equipo.get());
					} else {
						Equipo equipoNuevo = new Equipo();
						equipoNuevo.setNombreEquipo(persona.nombreEquipo());
						Equipo e = equipoRepositorio.save(equipoNuevo);
						nuevoEstudiante.setEquipo(e);
					}
				}
				System.out.println(nuevoEstudiante);
				return personaRepositorio.save(nuevoEstudiante);
			case Profesor:
				Profesor nuevoProfesor = new Profesor();
				nuevoProfesor.setMatricula(persona.matricula());
				nuevoProfesor.setEmail(persona.email());
				nuevoProfesor.setPassword(encriptador.encode(persona.contrasenia()));
				nuevoProfesor.setNombre(nombres);
				nuevoProfesor.setApellido(apellidos);
				nuevoProfesor.setFoto(fotoPerfil.getBytes());
				return personaRepositorio.save(nuevoProfesor);
		}
		return null;
	}

}
