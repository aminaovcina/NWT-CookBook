package com.example.demo.controllers;

import java.util.List;

import com.example.demo.errors.exception.FavoriteAlreadyExistsException;
import com.example.demo.errors.exception.FavoriteNotFoundException;
import com.example.demo.models.Favorites;
import com.example.demo.repositories.FavoritesRepository;
import com.example.demo.services.FavoritesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/favs")
public class FavoritesController {
    @Autowired
    public FavoritesService favoritesService;

    @PostMapping(path = "/add")
    public @ResponseBody Favorites saveFavorite(@RequestBody Favorites f) {
        List<Favorites> lista = favoritesService.getFavoritesByUser(f.getIdUser());
        for (Favorites s : lista) {
            if(s.getIdRecipe().equals(f.getIdRecipe()))
                throw new FavoriteAlreadyExistsException(s.getIdUser(), s.getIdRecipe());
        }
        favoritesService.save(f);
        return f;
    }

    @GetMapping(path = "/user/{idUser}")
    public @ResponseBody List<Favorites> getFavoritesByUser(@PathVariable("idUser") Integer id) {
        List<Favorites> lista;
        try{
            lista=favoritesService.getFavoritesByUser(id);
            if(lista.size()==0) throw new FavoriteNotFoundException("User s ID-em " + id + "nije sačuvao nijedan recept");
        } catch(Exception k){
        throw new FavoriteNotFoundException("User s ID-em " + id + "nije sačuvao nijedan recept");
    }
        return lista;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Favorites getFavoriteById(@PathVariable("id") Integer id) {
        Favorites f;
        try{
            f=favoritesService.getFavoritesById(id); 
        } catch(Exception k){
        throw new FavoriteNotFoundException("Favorite s ID-em " + id + "nije pronađen");
    }
        return f;
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Favorites> getAllFavorites() {
        List<Favorites> lista;
        try{
            lista=favoritesService.getAllFavorites();
            if(lista.size()==0) throw new FavoriteNotFoundException("NEma favorite-a.");
        } catch(Exception k){
        throw new FavoriteNotFoundException("Nema favorite-a.");
    }
        return lista;
    }

    @DeleteMapping("/delete/{id}")
    private void deleteFavorites(@PathVariable("id") Integer id) {
        try {
            favoritesService.delete(id);
        } catch (Exception k) {
            throw new FavoriteNotFoundException("Favorite: " + id + " not Found");
        }
    }

}