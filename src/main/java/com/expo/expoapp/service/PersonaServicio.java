package com.expo.expoapp.service;

import com.expo.expoapp.model.Persona;
import com.expo.expoapp.request.PersonaSolicitud;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PersonaServicio {
    public List<Persona> listar();
    public Persona crearUsuario(PersonaSolicitud persona, MultipartFile fotoPerfil) throws IOException;
}
