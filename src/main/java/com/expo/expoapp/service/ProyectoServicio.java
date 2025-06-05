package com.expo.expoapp.service;

import com.expo.expoapp.request.ProyectoSolicitud;
import com.expo.expoapp.response.ProyectoRespuesta;
import java.util.List;

public interface ProyectoServicio {
	public List<ProyectoRespuesta> listar();
	public ProyectoRespuesta crearProyecto(String boleta, ProyectoSolicitud proyectoSolicitud);
}
