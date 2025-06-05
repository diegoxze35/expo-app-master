package com.expo.expoapp.controller;

import com.expo.expoapp.model.Proyecto;
import com.expo.expoapp.repository.ProyectoRepositorio;
import com.expo.expoapp.request.ProyectoSolicitud;
import com.expo.expoapp.response.ProyectoRespuesta;
import com.expo.expoapp.service.ProyectoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

	private final ProyectoRepositorio proyectoRepositorio;
	private final ProyectoServicio proyectoServicio;

	@Autowired
	public ProyectoControlador(ProyectoRepositorio proyectoRepositorio, ProyectoServicio proyectoServicio) {
		this.proyectoRepositorio = proyectoRepositorio;
		this.proyectoServicio = proyectoServicio;
	}

	@GetMapping
	public List<ProyectoRespuesta> listar() {
		return proyectoServicio.listar();
	}

	@PostMapping
	public ResponseEntity<ProyectoRespuesta> crear(@RequestParam String boleta, @RequestBody ProyectoSolicitud proyecto) {
		ProyectoRespuesta resultado = this.proyectoServicio.crearProyecto(boleta, proyecto);
		return resultado == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(resultado);
	}

	@GetMapping("/{id}")
	public Proyecto obtener(@PathVariable Long id) {
		return proyectoRepositorio.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Proyecto actualizar(@PathVariable Long id, @RequestBody Proyecto proyecto) {
		proyecto.setIdProyecto(id);
		return proyectoRepositorio.save(proyecto);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		proyectoRepositorio.deleteById(id);
	}
}
