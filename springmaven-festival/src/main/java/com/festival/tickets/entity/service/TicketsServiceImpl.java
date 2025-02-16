package com.festival.tickets.entity.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.tickets.entity.DAO.ITicketsDAO;
import com.festival.tickets.entity.models.GeneradorDeSeriales;
import com.festival.tickets.entity.models.Tickets;

@Service
public class TicketsServiceImpl implements ITicketsService{
	
	@Autowired
    private ITicketsDAO ticketsDao;

	@Override
	public Tickets get(long id) {
		return ticketsDao.findById(id).orElse(null);
	}

	@Override
	public List<Tickets> getAll() {
		 return (List<Tickets>) ticketsDao.findAll();
	}

	@Override
    public void post(Tickets ticket) {
        String generatedIdentifier = GeneradorDeSeriales.generarSerial(7);  
        ticket.setIdentifier(generatedIdentifier);
        ticket.setAdquirido(false);
        ticketsDao.save(ticket);
    }

	@Override
	public void put(Tickets ticket, long id) {
		Optional<Tickets> existingTicketsOpt = ticketsDao.findById(id);
        
        if (existingTicketsOpt.isPresent()) {
        	Tickets existingTickets = existingTicketsOpt.get();
        	existingTickets.setType(ticket.getType());
        	existingTickets.setPrice(ticket.getPrice());
            ticketsDao.save(existingTickets);
        } else {
            throw new RuntimeException("Ticket no encontrado para actualizar");
        }
		
	}

	@Override
	public void delete(long id) {
		if (ticketsDao.existsById(id)) {
			ticketsDao.deleteById(id);
        } else {
            throw new RuntimeException("Ticket no encontrado para eliminar");
        }
		
	}
	
	@Override
	public void marcarComoAdquirido(long idTicket) {
	    Optional<Tickets> ticketOpt = ticketsDao.findById(idTicket);
	    if (ticketOpt.isPresent()) {
	        Tickets ticket = ticketOpt.get();
	        ticket.setAdquirido(true);  
	        ticketsDao.save(ticket);  
	    } else {
	        throw new RuntimeException("Ticket no encontrado para marcar como adquirido");
	    }
	}

	@Override
	public List<Tickets> findByType(String type) {
	    return ticketsDao.findByType(type);
	}
	
	

	

}
