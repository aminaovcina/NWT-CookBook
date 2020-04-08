package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Favorites;
import com.example.demo.repositories.FavoritesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    public List<Favorites> getAllFavorites() {
        return favoritesRepository.findAll();
    }

    public Favorites getFavoritesById(int id) {
        return favoritesRepository.findById(id).get();
    }

    public List<Favorites> getFavoritesByUser(int id) {
        return favoritesRepository.findByIdUser(id);
    }
    
    public List<Favorites> getFavoritesByRecipe(int idRecipe) {
        return favoritesRepository.findByIdRecipe(idRecipe);
    }

    public void saveOrUpdate(Favorites fav) {
        favoritesRepository.save(fav);
    }

    public void delete(int id) {
        favoritesRepository.deleteById(id);
    }

    public void deleteByIdUSer(Integer id){
        List<Favorites> lista = getFavoritesByUser(id);
        for (Favorites fav : lista) {
            delete(fav.getId());
        }
    }

    public void deleteByIdRecipe(Integer id){
        List<Favorites> lista = getFavoritesByRecipe(id);
        for (Favorites fav : lista) {
            delete(fav.getId());
        }
    }

    public Favorites createFavorite(int idUser, int idRecipe){
        Favorites fav = new Favorites(idUser, idRecipe);
        favoritesRepository.save(fav);
        return fav;
    }
}