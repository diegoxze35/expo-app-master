package com.expo.expoapp.controller;

import com.expo.expoapp.model.Equipo;
import com.expo.expoapp.repository.EquipoRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoControlador {

    private final EquipoRepositorio repository;

    public EquipoControlador(EquipoRepositorio repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Equipo> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Equipo crear(@RequestBody Equipo equipo) {
        return repository.save(equipo);
    }

    @GetMapping("/{id}")
    public Equipo obtener(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Equipo actualizar(@PathVariable Long id, @RequestBody Equipo equipo) {
        equipo.setIdEquipo(id);
        return repository.save(equipo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
