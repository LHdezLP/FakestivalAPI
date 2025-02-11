package com.festival.tickets.entity.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.festival.tickets.entity.DAO.ICompraDAO;
import com.festival.tickets.entity.DAO.ICompradorDAO;
import com.festival.tickets.entity.DAO.ITicketsDAO;
import com.festival.tickets.entity.models.Compra;
import com.festival.tickets.entity.models.Comprador;
import com.festival.tickets.entity.models.Tickets;
import com.festival.tickets.entity.models.GeneradorDeSeriales;

@Service
public class CompraServiceImpl implements ICompraService {

	@Autowired
	private ICompraDAO compraDao;

	@Autowired
	private ICompradorDAO compradorDao;

	@Autowired
	private ITicketsDAO ticketsDao;

	@Override
	public Compra get(long id) {
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public List<Compra> getAll() {
		return (List<Compra>) compraDao.findAll();
	}

	@Override
	public Compra getByIdentificador(String identificador) {
		return compraDao.findByIdentificador(identificador).orElse(null);
	}

	@Override
	public void post(Compra compra) {

		Comprador compradorExistente = compradorDao.findById(compra.getComprador().getId()).orElseThrow(
				() -> new IllegalArgumentException("Comprador no encontrado con ID: " + compra.getComprador().getId()));

		List<Tickets> ticketsExistentes = compra.getTickets().stream().map(ticket -> ticketsDao.findById(ticket.getId())
				.orElseThrow(() -> new IllegalArgumentException("Ticket no encontrado con ID: " + ticket.getId())))
				.toList();

		ticketsExistentes.forEach(ticket -> ticket.setAdquirido(true));
		ticketsDao.saveAll(ticketsExistentes);

		compra.setComprador(compradorExistente);
		compra.setTickets(ticketsExistentes);
		compra.setIdentificador(GeneradorDeSeriales.generarSerial(7));

		compraDao.save(compra);
	}

	@Override
	public void put(Compra compra, long id) {
		Compra existingCompra = compraDao.findById(id)
				.orElseThrow(() -> new RuntimeException("Compra no encontrada para actualizar"));

		existingCompra.setFecha(LocalDateTime.now());
		existingCompra.setComprador(compra.getComprador());

		List<Tickets> ticketsActualizados = compra.getTickets().stream()
				.map(ticket -> ticketsDao.findById(ticket.getId()).orElseThrow(
						() -> new IllegalArgumentException("Ticket no encontrado con ID: " + ticket.getId())))
				.toList();

		existingCompra.setTickets(ticketsActualizados);
		compraDao.save(existingCompra);
	}

	@Override
	public void delete(long id) {
		Compra compra = compraDao.findById(id)
				.orElseThrow(() -> new RuntimeException("Compra no encontrada para eliminar"));

		compra.getTickets().forEach(ticket -> {
			ticket.setAdquirido(false);
			ticketsDao.save(ticket);
		});

		compraDao.deleteById(id);
	}

	@Override
	public void eliminarTicketDeCompra(Long idCompra, Long idTicket) {

		Compra compra = compraDao.findById(idCompra)
				.orElseThrow(() -> new RuntimeException("Compra no encontrada con id: " + idCompra));
		Tickets ticket = ticketsDao.findById(idTicket)
				.orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + idTicket));
		if (!compra.getTickets().contains(ticket)) {
			throw new IllegalArgumentException("El ticket no pertenece a esta compra");
		}
		compra.getTickets().remove(ticket);
		ticket.setAdquirido(false);
		ticketsDao.save(ticket);
		compraDao.save(compra);
	}

}
