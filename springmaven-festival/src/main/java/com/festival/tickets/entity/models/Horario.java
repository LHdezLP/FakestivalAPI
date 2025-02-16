package com.festival.tickets.entity.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario;

    private String nombre;


    @OneToMany(mappedBy = "horario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<HorarioBanda> horarioBandas;

    public Horario() {}

    public Horario(String nombre) {
        this.nombre = nombre;
        this.horarioBandas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public List<HorarioBanda> getHorarioBandas() {
        return horarioBandas;
    }

    public void setHorarioBandas(List<HorarioBanda> horarioBandas) {
        this.horarioBandas = horarioBandas;
    }

    public Long getId_horario() {
        return id_horario;
    }

    public void setId_horario(Long id_horario) {
        this.id_horario = id_horario;
    }
}
