package com.expo.expoapp.controller;

import com.expo.expoapp.model.Evento;
import com.expo.expoapp.repository.EventoRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoControlador {

    private final EventoRepositorio repository;

    public EventoControlador(EventoRepositorio repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Evento> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Evento crear(@RequestBody Evento evento) {
        return repository.save(evento);
    }

    @GetMapping("/{id}")
    public Evento obtener(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Evento actualizar(@PathVariable Long id, @RequestBody Evento evento) {
        evento.setIdEvento(id);
        return repository.save(evento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
