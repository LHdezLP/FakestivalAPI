package com.festival.tickets.entity.service;

import java.util.List;

import com.festival.tickets.entity.models.Comprador;

public interface ICompradorService {
	public Comprador get(long id);
    public List<Comprador> getAll();
    public Comprador post(Comprador comprador);
    public void put(Comprador comprador, long id);
    public void delete(long id);
    public Comprador getByDni(String dni);
}
