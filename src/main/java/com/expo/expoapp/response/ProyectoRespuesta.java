package com.expo.expoapp.response;

import java.time.LocalDate;
import java.util.List;

public record ProyectoRespuesta(
    String titulo,
    String descripcion,
    LocalDate fechaExposicion,
	String nombreEquipo,
	List<String> integrantes

) {
}
