package com.festival.tickets.entity.service;

import java.util.List;

import com.festival.tickets.entity.models.Banda;

public interface IBandaService {
    public Banda get(long id); // Obtener una banda por su ID
    public List<Banda> getAll(); // Obtener todas las bandas
    public void post(Banda banda); // Crear una nueva banda
    public void put(Banda banda, long id); // Actualizar una banda existente
    public void delete(long id); // Eliminar una banda por su ID
}
