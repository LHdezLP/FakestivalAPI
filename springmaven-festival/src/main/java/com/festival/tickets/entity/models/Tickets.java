package com.festival.tickets.entity.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ticket;

	private String type;

	@Column(unique = true)
	private String identifier;

	private double price;

	private boolean adquirido;

	public Long getId() {
		return id_ticket;
	}

	public void setId(Long id_ticket) {
		this.id_ticket = id_ticket;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAdquirido() {
		return adquirido;
	}

	public void setAdquirido(boolean adquidirdo) {
		this.adquirido = adquidirdo;
	}
	
	

	public Tickets() {

	}

}
