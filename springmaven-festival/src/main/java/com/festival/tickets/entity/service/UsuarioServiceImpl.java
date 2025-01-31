package com.festival.tickets.entity.service;

import com.festival.tickets.entity.DAO.IUsuarioDAO;
import com.festival.tickets.entity.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioDAO usuarioDao;

    @Override
    public Usuario get(long id) { return usuarioDao.findById(id).orElse(null);}


    @Override
    public List<Usuario> getAll() {return (List<Usuario>) usuarioDao.findAll();}

    @Override
    public Usuario post(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    @Override
    public void put(Usuario usuario, long id){
        Optional<Usuario> existingUsuarioOpt = usuarioDao.findById(id);

        if (existingUsuarioOpt.isPresent()) {
            Usuario existingUsuario = existingUsuarioOpt.get();
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            existingUsuario.setEmail(usuario.getEmail());
            existingUsuario.setTelefono(usuario.getTelefono());
            usuarioDao.save(existingUsuario);
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public void delete(long id) {
        if (usuarioDao.existsById(id)){
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
    @Override
    public Usuario getByContrasena(String contrasena) {return usuarioDao.findByContrasena(contrasena);}
}
