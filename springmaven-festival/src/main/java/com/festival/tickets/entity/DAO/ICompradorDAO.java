package com.festival.tickets.entity.DAO;

import org.springframework.data.repository.CrudRepository;

import com.festival.tickets.entity.models.Comprador;

public interface ICompradorDAO extends CrudRepository<Comprador, Long> {
	Comprador findByDni(String dni);

}
