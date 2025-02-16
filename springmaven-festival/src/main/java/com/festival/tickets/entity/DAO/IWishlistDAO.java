package com.festival.tickets.entity.DAO;

import com.festival.tickets.entity.models.Wishlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IWishlistDAO extends CrudRepository<Wishlist, Long> {
    List<Wishlist> findByUsuarioId(Long usuarioId);
}