package com.expo.expoapp.repository;

import com.expo.expoapp.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepositorio extends JpaRepository<Evento, Long> {
}
