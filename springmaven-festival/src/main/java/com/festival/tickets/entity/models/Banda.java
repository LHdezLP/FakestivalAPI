package com.festival.tickets.entity.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bandas")
public class Banda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_banda;

    private String nombre;
    private String escenario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FranjaHoraria franjaHoraria;

    @OneToMany(mappedBy = "banda", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<HorarioBanda> horarioBandas;

    public Banda() {}

    public Banda(String nombre, String escenario, FranjaHoraria franjaHoraria) {
        this.nombre = nombre;
        this.escenario = escenario;
        this.franjaHoraria = franjaHoraria;
        this.horarioBandas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscenario() {
        return escenario;
    }

    public void setEscenario(String escenario) {
        this.escenario = escenario;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public List<HorarioBanda> getHorarioBandas() {
        return horarioBandas;
    }

    public void setHorarioBandas(List<HorarioBanda> horarioBandas) {
        this.horarioBandas = horarioBandas;
    }

    public Long getId_banda() {
        return id_banda;
    }

    public void setId_banda(Long id_banda) {
        this.id_banda = id_banda;
    }
}

