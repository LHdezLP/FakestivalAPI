package com.festival.tickets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.festival.tickets.entity.models.Tickets;
import com.festival.tickets.entity.service.ITicketsService;

@CrossOrigin(origins = "*")
@RestController
public class TicketsController {
	
	@Autowired
    ITicketsService ticketsService;

    @GetMapping("/tickets")
    public List<Tickets> getAllTickets() {
        return ticketsService.getAll();
    }

    @GetMapping("/tickets/{id}")
    public Tickets getOne(@PathVariable(value = "id") long id) {
        return ticketsService.get(id);
    }

    @PostMapping("/tickets")
    public void post(@RequestBody Tickets student) {  
    	ticketsService.post(student);
    }

    @PutMapping("/tickets/{id}")
    public void put(@PathVariable(value = "id") long id, @RequestBody Tickets ticket) {  
    	ticketsService.put(ticket, id);
    }

    @DeleteMapping("/tickets/{id}")
    public void delete(@PathVariable(value = "id") long id) {
    	ticketsService.delete(id);
    }
    
    @PutMapping("/tickets/adquirir/{id}")
    public void marcarComoAdquirido(@PathVariable("id") long idTicket) {
        ticketsService.marcarComoAdquirido(idTicket);
    }
    
    @GetMapping("/tickets/type/{type}")
    public List<Tickets> getTicketsByType(@PathVariable("type") String type) {
        return ticketsService.findByType(type);
    }
    
    

}
