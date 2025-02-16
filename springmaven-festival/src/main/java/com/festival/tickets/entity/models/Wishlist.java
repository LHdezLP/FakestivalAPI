package com.festival.tickets.entity.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_wishlist;

    @Column(nullable = false)
    private String nombre;

    @ElementCollection
    @CollectionTable(name = "wishlist_bandas", joinColumns = @JoinColumn(name = "id_wishlist"))
    @Column(name = "banda")
    private List<String> bandas;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Getters y Setters

    public Long getId() {
        return id_wishlist;
    }

    public void setId(Long id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getBandas() {
        return bandas;
    }

    public void setBandas(List<String> bandas) {
        this.bandas = bandas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
