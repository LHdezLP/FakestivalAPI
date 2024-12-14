package com.festival.tickets.entity.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Fetch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Compra {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_compra;
    
    private LocalDateTime fecha;
    
    @Column(unique = true)
    private String identificador;

    @ManyToOne
    @JoinColumn(name = "id_comprador")
    private Comprador comprador;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_compra")
    private List<Tickets> tickets;

    
    public Compra() {
    }

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id_compra;
    }

    public void setId(Long id) {
        this.id_compra = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

    
}
