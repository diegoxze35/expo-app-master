package com.expo.expoapp.repository;

import com.expo.expoapp.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepositorio extends JpaRepository<Horario, Long> {
}
