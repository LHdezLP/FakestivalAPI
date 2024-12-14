package com.festival.tickets.entity.DAO;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.festival.tickets.entity.models.Compra;

public interface ICompraDAO extends CrudRepository<Compra, Long>{
	 Optional<Compra> findByIdentificador(String identificador);

}
