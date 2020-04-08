package com.example.demo.repositories;

import java.util.List;

import com.example.demo.models.Favorites;

import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
    
    public List<Favorites> findByIdUser(Integer idUser);
    public List<Favorites> findByIdRecipe(Integer idRecipe);
}