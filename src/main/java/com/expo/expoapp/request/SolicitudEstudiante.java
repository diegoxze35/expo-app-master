package com.expo.expoapp.request;

public record SolicitudEstudiante(
		String boleta,
		String nombre,
		String apellido,
		String correo,
		String grupo,
		String turno,
		String nombreEquipo
) {
}
