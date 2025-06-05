package com.expo.expoapp.request;

import com.expo.expoapp.model.Carrera;

public record PersonaSolicitud(
    TipoUsuario tipoUsuario,
    String nombre,
    String email,
    String matricula,
    String contrasenia,
    String grupo,
    Integer semestre,
    Carrera carrera,
    String nombreEquipo
) {
}

/*
 * {
 *   "tipoUsuario": "Estudiante",
 *   "nombre": "Diego Alejandro Moreno Martinez",
 *   "email": "diegoalv1217@gmail.com",
 *   "matricula": "2022101217",
 *   "contrasenia": "12345678",
 *   "grupo": "5CV2",
 *   "semestre": "5",
 *   "carrera": "ISC",
 *   "nombreEquipo": "Los Programadores"
 * }
 * 
 * {
 *   "tipoUsuario": "Profesor",
 *   "nombre": "Diego Alejandro Moreno Martinez",
 *   "email": "diegoalv1217@gmail.com",
 *   "matricula": "2022101217",
 *   "contrasenia": "12345678",
 *   "grupo": null,
 *   "semestre": null,
 *   "carrera": null,
 *   "nombreEquipo": null
 * }
 * 
 */