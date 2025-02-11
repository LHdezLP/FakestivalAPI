package com.festival.tickets.entity.service;

import java.util.List;

import com.festival.tickets.entity.models.Compra;

public interface ICompraService {
	public Compra get(long id);
    public List<Compra> getAll();
    public void post(Compra compra);
    public void put(Compra compra, long id);
    public void delete(long id);
    public Compra getByIdentificador(String identificador);
    public void eliminarTicketDeCompra(Long idCompra, Long idTicket);
}
