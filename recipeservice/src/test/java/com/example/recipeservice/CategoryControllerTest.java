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
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getAllCategories() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/category")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("EasyMeal")))
                .andExpect(jsonPath("$[1].id", is(4)))
                .andExpect(jsonPath("$[1].name", is("NoBaking")))
                ;
    }
    @Test
    public void getCategoryByIdBadParamete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/category/-12")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
              ;
    }
    @Test
    public void getCategoryByIdBadParameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/category/fkdsj")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }
}
