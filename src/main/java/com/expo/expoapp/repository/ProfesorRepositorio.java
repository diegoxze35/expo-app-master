package com.expo.expoapp.repository;

import com.expo.expoapp.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor, String> {
}
