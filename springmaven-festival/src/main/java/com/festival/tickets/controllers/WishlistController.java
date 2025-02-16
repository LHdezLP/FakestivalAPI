package com.festival.tickets.controllers;

import com.festival.tickets.entity.models.Usuario;
import com.festival.tickets.entity.models.Wishlist;
import com.festival.tickets.entity.service.IUsuarioService;
import com.festival.tickets.entity.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private IWishlistService wishlistService;

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping
    public List<Wishlist> getAll() {
        return wishlistService.getAll();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Wishlist> getAllByUsuarioId(@PathVariable Long usuarioId) {
        return wishlistService.getAllByUsuarioId(usuarioId);
    }

    @GetMapping("/{id}")
    public Wishlist getOne(@PathVariable Long id) {
        return wishlistService.get(id);
    }

    @PostMapping
    public ResponseEntity<Wishlist> create(@RequestBody Wishlist wishlist) {
        if (wishlist.getUsuario() == null || wishlist.getUsuario().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Usuario usuarioExistente = usuarioService.get(wishlist.getUsuario().getId());

        if (usuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


        wishlist.setUsuario(usuarioExistente);

        Wishlist nuevaWishlist = wishlistService.post(wishlist);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaWishlist);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Wishlist wishlist) {
        wishlistService.put(wishlist, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        wishlistService.delete(id);
    }
}