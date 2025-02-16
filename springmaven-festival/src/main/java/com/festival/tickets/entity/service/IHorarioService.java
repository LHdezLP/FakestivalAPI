package com.festival.tickets.entity.service;

import java.util.List;

import com.festival.tickets.entity.models.Horario;

public interface IHorarioService {
    public Horario get(long id);
    public List<Horario> getAll();
    public void post(Horario horario);
    public void put(Horario horario, long id);
    public void delete(long id);
}
