package com.example.recipeservice;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.recipeservice.models.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getAllCRecipes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/recipe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(5)))
                .andExpect(jsonPath("$[0].title", is("Klepe")))
                .andExpect(jsonPath("$[0].description", is("Klepice slatke male")))
                .andExpect(jsonPath("$[0].cookingTemperature", is(270)))
                .andExpect(jsonPath("$[0].cookingTime", is(30)))
                .andExpect(jsonPath("$[1].id", is(6)))
                .andExpect(jsonPath("$[1].title", is("Tufahija")))
                .andExpect(jsonPath("$[1].description", is("Tufahije za pola sata")))
                .andExpect(jsonPath("$[1].cookingTemperature", is(270)))
                .andExpect(jsonPath("$[1].cookingTime", is(30)))
                ;
    }
    @Test
    @Order(2)
    public void getRecipeById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/recipe/6")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.title", is("Tufahija")))
                .andExpect(jsonPath("$.description", is("Tufahije za pola sata")))
                .andExpect(jsonPath("$.cookingTemperature", is(270)))
                .andExpect(jsonPath("$.cookingTime", is(30)))
        ;
    }
    String res;
    Recipe newRecipe;

    public void convertResToRecipe() {
        ObjectMapper m = new ObjectMapper();

        try {
            newRecipe = m.readValue(res, Recipe.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String convertRecipeToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new Recipe("KlepeZaBrisanje", "Klepice slatke male za brisanje", 30, 270, null, null));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    @Order(3)
    public void saveOrUpdateRecipe() throws Exception {
        MvcResult r = mvc.perform(MockMvcRequestBuilders.post("/recipe/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertRecipeToJson()))
                .andExpect(status().isOk())
                .andReturn()
                ;
        res = r.getResponse().getContentAsString();
        convertResToRecipe();
    }
    @Test
    @Order(4)
    public void deleteRecipe() throws Exception {
        saveOrUpdateRecipe();
        String url = "/recipe/delete/"+newRecipe.getId().toString();
        mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(status().isOk());
    }
    @Test
	@Order(5)
	public void shouldShow400() throws Exception {

	
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/recipe/h")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertRecipeToJson()))
		.andExpect(status().isBadRequest())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToRecipe();
    }
    @Test
	@Order(6)
	public void shouldShow400Delete() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.delete("/recipe/delete/k")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertRecipeToJson()))
		.andExpect(status().isBadRequest())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToRecipe();
    }
    @Test
	@Order(7)
	public void MethodNotAllowed() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/recipe/delete/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertRecipeToJson()))
		.andExpect(status().isMethodNotAllowed())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToRecipe();
	}

}
