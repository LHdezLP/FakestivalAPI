package com.festival.tickets.entity.service;

import com.festival.tickets.entity.DAO.IWishlistDAO;
import com.festival.tickets.entity.models.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements IWishlistService {

    @Autowired
    private IWishlistDAO wishlistDao;

    @Override
    public List<Wishlist> getAll() {
        return (List<Wishlist>) wishlistDao.findAll();
    }


    @Override
    public Wishlist get(long id) {
        return wishlistDao.findById(id).orElse(null);
    }

    @Override
    public List<Wishlist> getAllByUsuarioId(Long usuarioId) {
        return wishlistDao.findByUsuarioId(usuarioId);
    }

    @Override
    public Wishlist post(Wishlist wishlist) {
        if (wishlist.getBandas() == null || wishlist.getBandas().isEmpty()) {
            throw new IllegalArgumentException("La wishlist debe contener al menos una banda.");
        }
        return wishlistDao.save(wishlist);
    }

    @Override
    public void put(Wishlist wishlist, long id) {
        Optional<Wishlist> existingWishlistOpt = wishlistDao.findById(id);

        if (existingWishlistOpt.isPresent()) {
            Wishlist existingWishlist = existingWishlistOpt.get();
            existingWishlist.setNombre(wishlist.getNombre());
            existingWishlist.setBandas(wishlist.getBandas());
            wishlistDao.save(existingWishlist);
        } else {
            throw new RuntimeException("Wishlist no encontrada");
        }
    }

    @Override
    public void delete(long id) {
        if (wishlistDao.existsById(id)) {
            wishlistDao.deleteById(id);
        } else {
            throw new RuntimeException("Wishlist no encontrada");
        }
    }
}