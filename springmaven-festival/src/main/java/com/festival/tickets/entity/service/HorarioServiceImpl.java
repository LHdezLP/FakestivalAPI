package com.festival.tickets.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.tickets.entity.DAO.IHorarioDAO;
import com.festival.tickets.entity.models.Horario;

@Service
public class HorarioServiceImpl implements IHorarioService {

    @Autowired
    private IHorarioDAO horarioDao;

    @Override
    public Horario get(long id) {
        return horarioDao.findById(id).orElse(null);
    }

    @Override
    public List<Horario> getAll() {
        return (List<Horario>) horarioDao.findAll();
    }

    @Override
    public void post(Horario horario) {
        horarioDao.save(horario);
    }

    @Override
    public void put(Horario horario, long id) {
        Horario existingHorario = horarioDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado para actualizar"));


        existingHorario.setNombre(horario.getNombre());

        horarioDao.save(existingHorario);
    }

    @Override
    public void delete(long id) {
        horarioDao.deleteById(id);
    }
}