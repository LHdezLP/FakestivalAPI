package com.festival.tickets.entity.service;

import com.festival.tickets.entity.models.Wishlist;

import java.util.List;

public interface IWishlistService {
    public List<Wishlist> getAll();
    public Wishlist get(long id);
    public List<Wishlist> getAllByUsuarioId(Long usuarioId);
    public Wishlist post(Wishlist wishlist);
    public void put(Wishlist wishlist, long id);
    public void delete(long id);
}