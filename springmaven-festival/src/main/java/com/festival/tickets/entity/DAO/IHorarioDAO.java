package com.festival.tickets.entity.DAO;


import org.springframework.data.repository.CrudRepository;

import com.festival.tickets.entity.models.Horario;

public interface IHorarioDAO extends CrudRepository<Horario, Long> {
}
