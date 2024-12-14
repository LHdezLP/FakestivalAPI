package com.festival.tickets.entity.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.festival.tickets.entity.models.Tickets;

public interface ITicketsDAO extends CrudRepository<Tickets, Long>{
	
	@Query("SELECT t FROM Tickets t WHERE t.type = :type")
    List<Tickets> findByType(@Param("type") String type);

}
