package com.expo.expoapp.repository;

import com.expo.expoapp.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Persona, String> {
}
