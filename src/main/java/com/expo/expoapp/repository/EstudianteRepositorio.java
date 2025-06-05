package com.expo.expoapp.repository;

import com.expo.expoapp.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, String> {
}
