package com.expo.expoapp.service.impl;

import com.expo.expoapp.model.Estudiante;
import com.expo.expoapp.model.Proyecto;
import com.expo.expoapp.repository.EstudianteRepositorio;
import com.expo.expoapp.repository.ProyectoRepositorio;
import com.expo.expoapp.request.ProyectoSolicitud;
import com.expo.expoapp.response.ProyectoRespuesta;
import com.expo.expoapp.service.ProyectoServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProyectoServicioImpl implements ProyectoServicio {

	private final EstudianteRepositorio estudianteRepositorio;
	private final ProyectoRepositorio proyectoRepositorio;

	public ProyectoServicioImpl(EstudianteRepositorio estudianteRepositorio, ProyectoRepositorio proyectoRepositorio) {
		this.estudianteRepositorio = estudianteRepositorio;
		this.proyectoRepositorio = proyectoRepositorio;
	}

	@Override
	public List<ProyectoRespuesta> listar() {
		return proyectoRepositorio.findAll().stream().map(
				(p) -> new ProyectoRespuesta(
						p.getTitulo(),
						p.getDescripcion(),
						p.getFechaExposicion(),
						p.getEquipo().getNombreEquipo(),
						p.getEquipo().getIntegrantes().stream().map((e) ->
								e.getNombre() + " " + e.getApellido()
						).toList()
				)
		).toList();
	}

	@Override
	public ProyectoRespuesta crearProyecto(String boleta, ProyectoSolicitud proyectoSolicitud) {
		Optional<Estudiante> optionalEstudiante = estudianteRepositorio.findById(boleta);
		if (optionalEstudiante.isEmpty()) {
			return null;
		}
		Estudiante estudiante = optionalEstudiante.get();
		if (estudiante.getEquipo() == null) {
			return null;
		}

		Proyecto p = new Proyecto();
		p.setTitulo(proyectoSolicitud.titulo());
		p.setDescripcion(proyectoSolicitud.descripcion());
		p.setEquipo(estudiante.getEquipo());
		p.setFechaExposicion(proyectoSolicitud.fechaExposicion());

		Proyecto guardado = proyectoRepositorio.save(p);
		return new ProyectoRespuesta(
				guardado.getTitulo(),
				guardado.getDescripcion(),
				guardado.getFechaExposicion(),
				guardado.getEquipo().getNombreEquipo(),
				guardado
						.getEquipo()
						.getIntegrantes()
						.stream()
						.map((e) -> e.getNombre() + " " + e.getApellido())
						.toList()
		);
	}

}
