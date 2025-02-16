package com.festival.tickets.entity.service;

import com.festival.tickets.entity.DAO.IUsuarioDAO;
import com.festival.tickets.entity.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        usuario = usuarioDao.save(usuario);

        String token = getJWTToken(usuario.getEmail());

        usuario.setToken(token);

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
            usuarioDao.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
    @Override
    public Usuario getByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }


    @Override
    public String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //.setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
