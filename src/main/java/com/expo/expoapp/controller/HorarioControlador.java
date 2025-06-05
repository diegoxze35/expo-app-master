package com.expo.expoapp.controller;

import com.expo.expoapp.model.Evento;
import com.expo.expoapp.model.Horario;
import com.expo.expoapp.repository.EventoRepositorio;
import com.expo.expoapp.repository.HorarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/horarios")
public class HorarioControlador {

    private final HorarioRepositorio horarioRepositorio;
    private final EventoRepositorio eventoRepositorio;

    public HorarioControlador(HorarioRepositorio horarioRepositorio, EventoRepositorio eventoRepositorio) {
        this.horarioRepositorio = horarioRepositorio;
        this.eventoRepositorio = eventoRepositorio;
    }

    @GetMapping
    public List<Horario> listar() {
        return horarioRepositorio.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Horario horario) {
        if (horario.getEvento() == null || horario.getEvento().getIdEvento() == null) {
            return ResponseEntity.badRequest().body("Debes proporcionar un evento válido.");
        }

        Optional<Evento> eventoOpt = eventoRepositorio.findById(horario.getEvento().getIdEvento());

        if (eventoOpt.isPresent()) {
            horario.setEvento(eventoOpt.get());
            Horario guardado = horarioRepositorio.save(horario);
            return ResponseEntity.ok(guardado);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("No existe un evento con id: " + horario.getEvento().getIdEvento());
        }
    }


    @GetMapping("/{id}")
    public Horario obtener(@PathVariable Long id) {
        return horarioRepositorio.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        if (horario.getEvento() == null || horario.getEvento().getIdEvento() == null) {
            return ResponseEntity.badRequest().body("Debes proporcionar un evento válido.");
        }

        Optional<Evento> eventoOpt = eventoRepositorio.findById(horario.getEvento().getIdEvento());

        if (eventoOpt.isPresent()) {
            horario.setIdHorario(id);
            horario.setEvento(eventoOpt.get());
            Horario actualizado = horarioRepositorio.save(horario);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("No existe un evento con id: " + horario.getEvento().getIdEvento());
        }
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        horarioRepositorio.deleteById(id);
    }
}
