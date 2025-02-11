package com.festival.tickets.controllers;

import com.festival.tickets.entity.models.Usuario;
import com.festival.tickets.entity.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usuario")
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }
    @GetMapping("/usuario/{id}")
    public Usuario getOne(@PathVariable (value = "id") long id) {return usuarioService.get(id);}

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(encodedPassword);

        Usuario nuevoUsuario = usuarioService.post(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/usuario/{id}")
    public void put(@PathVariable (value = "id") long id, @RequestBody Usuario usuario) {
        usuarioService.put(usuario, id);
    }

    @DeleteMapping("/usuario/{id}")
    public void delete(@PathVariable (value = "id") long id) {usuarioService.delete(id);}



    @PostMapping("user")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String pwd) {

        Usuario usuario = usuarioService.getByEmail(email); // Necesitarás implementar este método en el servicio y repositorio

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }


        if (!passwordEncoder.matches(pwd, usuario.getContrasena())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }


        String token = usuarioService.getJWTToken(usuario.getEmail());


        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("usuario", usuario);

        return ResponseEntity.ok(response);
    }

}
