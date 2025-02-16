package com.festival.tickets.entity.models;

import jakarta.persistence.*;


@Entity
@Table(name = "horario_banda",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id_horario", "franja_horaria"})})
public class HorarioBanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario_banda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FranjaHoraria franjaHoraria;

    @ManyToOne
    @JoinColumn(name = "id_horario", nullable = false)
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "id_banda", nullable = false)
    private Banda banda;

    public HorarioBanda() {}

    public HorarioBanda(Horario horario, Banda banda, FranjaHoraria franjaHoraria) {
        this.horario = horario;
        this.banda = banda;
        this.franjaHoraria = franjaHoraria;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public Long getId_horario_banda() {
        return id_horario_banda;
    }

    public void setId_horario_banda(Long id_horario_banda) {
        this.id_horario_banda = id_horario_banda;
    }
}

