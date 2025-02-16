package com.festival.tickets.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.tickets.entity.DAO.IBandaDAO;
import com.festival.tickets.entity.models.Banda;

@Service
public class BandaServiceImpl implements IBandaService {

    @Autowired
    private IBandaDAO bandaDao;

    @Override
    public Banda get(long id) {
        return bandaDao.findById(id).orElse(null);
    }

    @Override
    public List<Banda> getAll() {
        return (List<Banda>) bandaDao.findAll();
    }

    @Override
    public void post(Banda banda) {
        bandaDao.save(banda);
    }

    @Override
    public void put(Banda banda, long id) {
        Banda existingBanda = bandaDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Banda no encontrada para actualizar"));


        existingBanda.setNombre(banda.getNombre());
        existingBanda.setEscenario(banda.getEscenario());
        existingBanda.setFranjaHoraria(banda.getFranjaHoraria());

        bandaDao.save(existingBanda);
    }

    @Override
    public void delete(long id) {
        bandaDao.deleteById(id);
    }
}