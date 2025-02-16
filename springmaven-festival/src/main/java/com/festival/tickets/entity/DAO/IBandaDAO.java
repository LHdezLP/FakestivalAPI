package com.festival.tickets.entity.DAO;

import org.springframework.data.repository.CrudRepository;

import com.festival.tickets.entity.models.Banda;

public interface IBandaDAO extends CrudRepository<Banda, Long> {
}