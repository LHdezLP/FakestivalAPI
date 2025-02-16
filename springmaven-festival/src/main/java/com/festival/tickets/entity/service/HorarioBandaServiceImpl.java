package com.festival.tickets.entity.service;

import com.festival.tickets.entity.DAO.IBandaDAO;
import com.festival.tickets.entity.DAO.IHorarioBandaDAO;
import com.festival.tickets.entity.DAO.IHorarioDAO;
import com.festival.tickets.entity.models.Banda;
import com.festival.tickets.entity.models.Horario;
import com.festival.tickets.entity.models.HorarioBanda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioBandaServiceImpl implements IHorarioBandaService {

    @Autowired
    private IHorarioBandaDAO horarioBandaDao;

    @Autowired
    private IBandaDAO bandaDao;

    @Autowired
    private IHorarioDAO horarioDao;

    @Override
    public HorarioBanda get(long id) {
        return horarioBandaDao.findById(id).orElse(null);
    }

    @Override
    public List<HorarioBanda> getAll() {
        return (List<HorarioBanda>) horarioBandaDao.findAll();
    }

    @Override
    public void post(HorarioBanda horarioBanda) {
        // Verificar que la banda existe y recuperar la banda
        Banda banda = bandaDao.findById(horarioBanda.getBanda().getId_banda())
                .orElseThrow(() -> new RuntimeException("Banda no encontrada"));

        // Verificar que la franja horaria coincide
        if (!banda.getFranjaHoraria().equals(horarioBanda.getFranjaHoraria())) {
            throw new RuntimeException("La banda no puede asignarse a esta franja horaria");
        }

        // Recuperar el Horario por ID desde la base de datos (en lugar de solo confiar en el ID)
        Horario horario = horarioBanda.getHorario();
        if (horario == null || !horarioDao.existsById(horario.getId_horario())) {
            throw new RuntimeException("Horario no válido o no encontrado");
        }

        // Verificar que no se dupliquen franjas horarias para el mismo horario
        if (horarioBandaDao.existsByHorarioAndFranjaHoraria(horario, horarioBanda.getFranjaHoraria())) {
            throw new RuntimeException("Ya existe una banda asignada a esta franja horaria en el horario");
        }

        // Guardar el objeto HorarioBanda
        horarioBandaDao.save(horarioBanda);
    }

    @Override
    public void put(HorarioBanda horarioBanda, long id) {
        HorarioBanda existingHorarioBanda = horarioBandaDao.findById(id)
                .orElseThrow(() -> new RuntimeException("HorarioBanda no encontrado para actualizar"));

        // Validar que la franja horaria de la banda coincida con la franja horaria del HorarioBanda
        Banda banda = bandaDao.findById(horarioBanda.getBanda().getId_banda())
                .orElseThrow(() -> new RuntimeException("Banda no encontrada"));
        if (!banda.getFranjaHoraria().equals(horarioBanda.getFranjaHoraria())) {
            throw new RuntimeException("La banda no puede asignarse a esta franja horaria");
        }

        // Validar que no se dupliquen franjas horarias en el mismo horario (excepto para el registro actual)
        if (horarioBandaDao.existsByHorarioAndFranjaHorariaAndIdHorarioBandaNot(
                horarioBanda.getHorario(), horarioBanda.getFranjaHoraria(), id)) {
            throw new RuntimeException("Ya existe una banda asignada a esta franja horaria en el horario");
        }

        existingHorarioBanda.setHorario(horarioBanda.getHorario());
        existingHorarioBanda.setBanda(horarioBanda.getBanda());
        existingHorarioBanda.setFranjaHoraria(horarioBanda.getFranjaHoraria());

        horarioBandaDao.save(existingHorarioBanda);
    }

    @Override
    public void delete(long id) {
        horarioBandaDao.deleteById(id);
    }
}