package com.expo.expoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProyecto;
    @Column(length = 50, nullable = false)
    private String titulo;
    @Column(length = 300, nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private LocalDate fechaExposicion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_equipo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Equipo equipo;
    
    // Getters y setters

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long id_proyecto) {
        this.idProyecto = id_proyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaExposicion() {
        return fechaExposicion;
    }

    public void setFechaExposicion(LocalDate fechaExposicion) {
        this.fechaExposicion = fechaExposicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
