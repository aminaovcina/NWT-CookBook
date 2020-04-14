package com.example.demo.errors.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FavoriteAlreadyExistsException extends RuntimeException{
    public FavoriteAlreadyExistsException(Integer idUser, Integer idRecipe){
        super("Favorite: " + idUser + "->"+ idRecipe+" already exists.");
    }
}