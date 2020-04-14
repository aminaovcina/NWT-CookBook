package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.example.demo.errors.exception.FavoriteAlreadyExistsException;
import com.example.demo.models.Favorites;
import com.example.demo.repositories.FavoritesRepository;
import com.example.demo.services.FavoritesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.spi.Message;

import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FavoritesControllerTests {
    @Autowired
    private MockMvc mmvc;
    private FavoritesRepository favoritesRepository;
    private FavoritesService favoritesService;
    String res;
    Favorites newFavorite;

    public void convertResToFavorite() {
        ObjectMapper m = new ObjectMapper();

        try {
            newFavorite = m.readValue(res, Favorites.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String convertFavoriteToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new Favorites(5, 6));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    @Order(1)
    public void getAllFavorites() throws Exception {
        mmvc.perform(MockMvcRequestBuilders.get("/favs/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idUser", is(2))).andExpect(jsonPath("$[0].idRecipe", is(1)))
                .andExpect(jsonPath("$[1].idUser", is(3))).andExpect(jsonPath("$[1].idRecipe", is(2)));
    }

    @Test
    @Order(2)
    public void getFavoriteById() throws Exception {
        mmvc.perform(MockMvcRequestBuilders.get("/favs/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.idUser", is(2)))
                .andExpect(jsonPath("$.idRecipe", is(1)));
    }

    @Test
    @Order(3)
    public void getFavoritesByUser() throws Exception {
        mmvc.perform(MockMvcRequestBuilders.get("/favs/user/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].idRecipe", is(1))).andExpect(jsonPath("$[0].idUser", is(2)));
    }

    @Test
    @Order(4)
    public void saveFavorite() throws Exception {
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.post("/favs/add")
                .contentType(MediaType.APPLICATION_JSON).content(convertFavoriteToJson())).andExpect(status().isOk())
                .andReturn();
        res = response.getResponse().getContentAsString();
        convertResToFavorite();
    }

    @Test
    @Order(5)
    public void deleteFavorite() throws Exception {
        
          List<Favorites> lista = favoritesRepository.findAll(); int size =
          lista.size(); int id = lista.get(size - 1).getId();
         
        //Favorites f = new Favorites(55, 66);
        
        mmvc.perform(
        delete("/favs/delete/{id}",id))
        .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void getFavoriteByUser_404_Greska() throws Exception {
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.get("/favs/user/6")
                .contentType(MediaType.APPLICATION_JSON).content(convertFavoriteToJson()))
                .andExpect(status().isNotFound()).andReturn();
        res = response.getResponse().getContentAsString();
        convertResToFavorite();
    }

    @Test
    @Order(7)
    public void getFavoriteById_400_Greska() throws Exception {
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.get("/favs/j").contentType(MediaType.APPLICATION_JSON)
                .content(convertFavoriteToJson())).andExpect(status().isBadRequest()).andReturn();
        res = response.getResponse().getContentAsString();
        convertResToFavorite();
    }

    @Test
    @Order(8)
    public void saveFavorite_Conflict_Greska() throws Exception {
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.post("/favs/add")
                .contentType(MediaType.APPLICATION_JSON).content(convertFavoriteToJson()))
                .andExpect(status().isConflict()).andReturn();
        res = response.getResponse().getContentAsString();
        convertResToFavorite();
    }

    @Test
    @Order(9)
    public void deleteFavorite_404_Greska() throws Exception {
        List<Favorites> lista = favoritesRepository.findAll();
        int size=lista.size();
        int id= lista.get(size-1).getId()+1;
        mmvc.perform(MockMvcRequestBuilders.delete("/favs/delete/" + Integer.toString(id))).andExpect(status().isNotFound());
    }
}