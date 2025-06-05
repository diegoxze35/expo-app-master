package com.expo.expoapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoEvento;

    // Getters y Setters
    public Long getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(Long id_evento) {
        this.idEvento = id_evento;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fecha_inicio) {
        this.fechaInicio = fecha_inicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fecha_fin) {
        this.fechaFin = fecha_fin;
    }
    public String getTipoEvento() {
        return tipoEvento;
    }
    public void setTipoEvento(String tipo_evento) {
        this.tipoEvento = tipo_evento;
    }
}
