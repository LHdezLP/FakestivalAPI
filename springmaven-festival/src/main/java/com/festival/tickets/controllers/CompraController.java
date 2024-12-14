package com.festival.tickets.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.festival.tickets.entity.models.Compra;
import com.festival.tickets.entity.service.ICompraService;

@CrossOrigin(origins = "*")
@RestController
public class CompraController {

	@Autowired
	ICompraService compraService;

	@GetMapping("/compras")
	public List<Compra> getAllCompras() {
		return compraService.getAll();
	}

	@GetMapping("/compras/{id}")
	public ResponseEntity<Compra> getOne(@PathVariable(value = "id") long id) {
		Compra compra = compraService.get(id);
		if (compra == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(compra, HttpStatus.OK);
	}

	@GetMapping("/compras/identificador/{identificador}")
	public ResponseEntity<Compra> getByIdentificador(@PathVariable(value = "identificador") String identificador) {
		Compra compra = compraService.getByIdentificador(identificador);
		if (compra == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(compra, HttpStatus.OK);
	}

	@PostMapping("/compras")
	public ResponseEntity<?> post(@RequestBody Compra compra) {
		try {
			compraService.post(compra);
			return new ResponseEntity<>(compra, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/compras/{id}")
	public ResponseEntity<String> put(@PathVariable(value = "id") long id, @RequestBody Compra compra) {
		try {
			compraService.put(compra, id);
			return new ResponseEntity<>("", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/compras/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable(value = "id") long id) {
		Map<String, String> response = new HashMap<>();
		try {
			compraService.delete(id);
			response.put("message", "Compra cancelada correctamente.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", "Error al cancelar la compra: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/compras/{id_compra}/tickets/{id_ticket}")
	public ResponseEntity<Map<String, String>> eliminarTicketDeCompra(@PathVariable(value = "id_compra") Long id_compra,
			@PathVariable(value = "id_ticket") Long id_ticket) {
		Map<String, String> response = new HashMap<>();
		try {
			compraService.eliminarTicketDeCompra(id_compra, id_ticket);
			response.put("message", "Ticket eliminado correctamente.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", "Error al eliminar el ticket: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
