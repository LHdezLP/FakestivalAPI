package com.festival.tickets.entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.tickets.entity.DAO.ICompradorDAO;
import com.festival.tickets.entity.models.Comprador;

@Service
public class CompradorServiceImpl implements ICompradorService {

	@Autowired	
	private ICompradorDAO compradorDao;

	@Override
	public Comprador get(long id) {
		return compradorDao.findById(id).orElse(null);
	}

	@Override
	public List<Comprador> getAll() {
		return (List<Comprador>) compradorDao.findAll();
	}

	@Override
	public Comprador post(Comprador comprador) {
		return compradorDao.save(comprador);

	}

	@Override
	public void put(Comprador comprador, long id) {
		Optional<Comprador> existingCompradoresOpt = compradorDao.findById(id);

		if (existingCompradoresOpt.isPresent()) {
			Comprador existingCompradores = existingCompradoresOpt.get();
			existingCompradores.setNombre(comprador.getNombre());
			existingCompradores.setApellido(comprador.getApellido());
			existingCompradores.setDireccion(comprador.getDireccion());
			existingCompradores.setDNI(comprador.getDNI());
			existingCompradores.setTelefono(comprador.getTelefono());
			existingCompradores.setEmail(comprador.getEmail());
			compradorDao.save(existingCompradores);
		} else {
			throw new RuntimeException("Comprador no encontrado para actualizar");
		}

	}

	@Override
	public void delete(long id) {
		if (compradorDao.existsById(id)) {
			compradorDao.deleteById(id);
        } else {
            throw new RuntimeException("Comprador no encontrado para eliminar");
        }

	}
	
	@Override
    public Comprador getByDni(String dni) {
        return compradorDao.findByDni(dni);
    }

}
