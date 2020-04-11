package com.example.recipeservice;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DishControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getAllDishes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dish")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Main dish")))
                .andExpect(jsonPath("$[0].description", is("Best part of the meal")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Dessert")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("Salad")))
                .andExpect(jsonPath("$[2].description", is("Healthy part of the meal")))
                ;
    }
    @Test
    public void getDish() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dish/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Main dish")))
                .andExpect(jsonPath("$.description", is("Best part of the meal")))
                ;
    }
    @Test
    public void getDishByIdBadParamete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/dish/-12")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
              ;
    }
}
