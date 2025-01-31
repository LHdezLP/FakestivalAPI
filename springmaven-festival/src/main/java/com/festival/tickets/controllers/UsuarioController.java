package com.festival.tickets.controllers;

import com.festival.tickets.entity.models.Usuario;
import com.festival.tickets.entity.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;
    @GetMapping("/usuario")
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }
    @GetMapping("/usuario/{id}")
    public Usuario getOne(@PathVariable (value = "id") long id) {return usuarioService.get(id);}

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.post(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/usuario/{id}")
    public void put(@PathVariable (value = "id") long id, @RequestBody Usuario usuario) {
        usuarioService.put(usuario, id);
    }

    @DeleteMapping("/usuario/{id}")
    public void delete(@PathVariable (value = "id") long id) {usuarioService.delete(id);}

    @GetMapping("/usuario/contrasena/{contrasena}")
    public ResponseEntity<Usuario> getUsuarioByContrasena(@PathVariable (value = "contrasena") String contrasena) {
        Usuario usuario = usuarioService.getByContrasena(contrasena);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(usuario);
    }

}
