package com.example.recipeservice;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.recipeservice.models.Category;
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
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getAllCategories() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/category")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Easy")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Homemade")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("NoBaking")))
                ;
    }
    @Test
    public void getCategory() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/category/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Easy")))
                ;
    }
    @Test
    public void getCategoryByIdBadParameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/category/fkdsj")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }
    String res;
    Category newCategory;

    public void convertResToCategory() {
        ObjectMapper m = new ObjectMapper();

        try {
            newCategory = m.readValue(res, Category.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
	public String convertCategoryToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new Category("testna"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
	}
    @Test
    public void saveCategory() throws Exception {
        MvcResult r = mvc.perform(MockMvcRequestBuilders.post("/category/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertCategoryToJson()))
                .andExpect(status().isOk())
                .andReturn()
                ;
        res = r.getResponse().getContentAsString();
        convertResToCategory();
	}
}
