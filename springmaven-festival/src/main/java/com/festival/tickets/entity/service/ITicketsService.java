package com.festival.tickets.entity.service;

import java.util.List;

import com.festival.tickets.entity.models.Tickets;

public interface ITicketsService {
	public Tickets get(long id);
    public List<Tickets> getAll();
    public void post(Tickets ticket);
    public void put(Tickets ticket, long id);
    public void delete(long id);
    public void marcarComoAdquirido(long id);
    public List<Tickets> findByType(String type);
    
}
