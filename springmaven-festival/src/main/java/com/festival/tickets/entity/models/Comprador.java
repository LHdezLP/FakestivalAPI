package com.festival.tickets.entity.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compradores")
public class Comprador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_comprador;

	private String nombre;
	
	private String apellido;
	
	private String direccion;
	
	@Column(unique = true)
	private String dni;
	
	private String telefono;
	
	private String email;

	public Long getId() {
		return id_comprador;
	}

	public void setId(Long id) {
		this.id_comprador = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDNI() {
		return dni;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Comprador() {
		
	}
	
	

}
