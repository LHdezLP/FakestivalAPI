package com.festival.tickets.entity.DAO;

import com.festival.tickets.entity.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
    Usuario findByContrasena(String contrasena);
}
