package com.example.demo.controllers;

import java.util.List;

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
    public @ResponseBody Favorites addNewFavorite(@RequestParam Integer idUser, @RequestParam Integer idRecipe) {
        Favorites s = new Favorites(idUser, idRecipe);
        favoritesService.saveOrUpdate(s);
        return s;
    }

    @GetMapping(path = "/user/{idUser}")
    public @ResponseBody List<Favorites> getFavoritsByUser(@PathVariable("idUser") Integer id) {
        return favoritesService.getFavoritesByUser(id);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Favorites getFavorites(@PathVariable("id") Integer id) {
        return favoritesService.getFavoritesById(id);
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Favorites> getAllFavorites() {
        return favoritesService.getAllFavorites();
    }

    @DeleteMapping("/delete/{id}")
    private void deleteFavorites(@RequestBody Integer id) {
        try {
            favoritesService.delete(id);
        } catch (Exception k) {
            throw new FavoriteNotFoundException("Favorite: " + id + " not Found");
        }
    }

    @DeleteMapping("/delete/user/{id}")
    private void deleteFavoritesByIdUser(@RequestBody Integer id) {
        try {
            favoritesService.deleteByIdUSer(id);
        } catch (Exception k) {
            throw new FavoriteNotFoundException("Favorites of user " + id + " are not Found");
        }
    }
}