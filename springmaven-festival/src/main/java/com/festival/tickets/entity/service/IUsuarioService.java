package com.festival.tickets.entity.service;

import com.festival.tickets.entity.models.Usuario;

import java.util.List;

public interface IUsuarioService {
    public Usuario get(long id);
    public List<Usuario> getAll();
    public Usuario post(Usuario usuario);
    public void put(Usuario usuario, long id);
    public void delete(long id);
    public Usuario getByEmail(String email);

    String getJWTToken(String username);
}
