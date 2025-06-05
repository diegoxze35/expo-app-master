package com.expo.expoapp.repository;

import com.expo.expoapp.model.Equipo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepositorio extends JpaRepository<Equipo, Long> {
	public Optional<Equipo> findByNombreEquipo(String nombreEquipo);
}
