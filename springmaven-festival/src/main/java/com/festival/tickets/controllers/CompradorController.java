package com.festival.tickets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.festival.tickets.entity.models.Comprador;
import com.festival.tickets.entity.service.ICompradorService;

@CrossOrigin(origins = "*")
@RestController
public class CompradorController {
	
	@Autowired
	ICompradorService compradorService;

	@GetMapping("/compradores")
	public List<Comprador> getAllTickets() {
		return compradorService.getAll();
	}

	@GetMapping("/compradores/{id}")
	public Comprador getOne(@PathVariable(value = "id") long id) {
		return compradorService.get(id);
	}

	@PostMapping("/compradores")
	public ResponseEntity<Comprador> post(@RequestBody Comprador comprador) {
	    Comprador nuevoComprador = compradorService.post(comprador); 
	    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComprador); 
	}

	@PutMapping("/compradores/{id}")
	public void put(@PathVariable(value = "id") long id, @RequestBody Comprador comprador) {
		compradorService.put(comprador, id);
	}

	@DeleteMapping("/compradores/{id}")
	public void delete(@PathVariable(value = "id") long id) {
		compradorService.delete(id);
	}
	
	@GetMapping("/compradores/dni/{dni}")
    public ResponseEntity<Comprador> getCompradorByDni(@PathVariable(value = "dni") String dni) {
        Comprador comprador = compradorService.getByDni(dni);
        if (comprador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(comprador);
    }
}
