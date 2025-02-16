package com.festival.tickets.entity.service;

import java.util.List;

import com.festival.tickets.entity.models.HorarioBanda;

public interface IHorarioBandaService {
    public HorarioBanda get(long id); // Obtener un horario-banda por su ID
    public List<HorarioBanda> getAll(); // Obtener todos los horarios-bandas
    public void post(HorarioBanda horarioBanda); // Crear un nuevo horario-banda
    public void put(HorarioBanda horarioBanda, long id); // Actualizar un horario-banda existente
    public void delete(long id); // Eliminar un horario-banda por su ID
}
