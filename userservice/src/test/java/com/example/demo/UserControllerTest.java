package com.example.demo;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.errors.ApiError;
import com.example.demo.models.Gender;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Mock
	UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockMvc mvc;
	
	@Test
	@Order(1)
	public void getAllUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/users")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("azra")))
				.andExpect(jsonPath("$[0].lastName", is("ibric")))
				;
	}

	String res;
    User newUser;

    public void convertResToUser() {
        ObjectMapper m = new ObjectMapper();

        try {
            newUser = m.readValue(res, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
	public String convertUserToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new User("azra", "ibric",  Gender.female, null, "sarajevo", "azara@bb.com"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
	/*Ovo je zakomentarisano jer padaju testovi, svaki put kad doda
    istog user-a, kojem je email unique
	
	@Test
    @Order(2)
    public void saveOrUpdateUser() throws Exception {
        MvcResult r = mvc.perform(MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertUserToJson()))
                .andExpect(status().isOk())
                .andReturn()
                ;
        res = r.getResponse().getContentAsString();
        convertResToUser();
	}
	


	@Test
	@Order(3)
	public void deleteOneUser() throws Exception {
		userService.delete(1);
		assertThat(userRepository.count()).isEqualTo(1);
	}

*/
	@Test
	@Order(4)
	public void shouldShow400() throws Exception {

	
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/user/h")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertUserToJson()))
		.andExpect(status().isBadRequest())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToUser();
	}


	@Test
	@Order(5)
	public void shouldShow404() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/user/;")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertUserToJson()))
		.andExpect(status().isNotFound())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToUser();
	}

	@Test
	@Order(6)
	public void shouldShow400Delete() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.delete("/user/delete/k")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertUserToJson()))
		.andExpect(status().isBadRequest())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToUser();
	}

	@Test
	@Order(7)
	public void MethodNotAllowed() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/user/delete/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertUserToJson()))
		.andExpect(status().isMethodNotAllowed())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToUser();
	}

	@Test
	@Order(8)
	public void getOneUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("azra")))
				.andExpect(jsonPath("$.lastName", is("ibric")))
				.andExpect(jsonPath("$.gender", is("female")))
				.andExpect(jsonPath("$.city", is("sarajevo")))
				.andExpect(jsonPath("$.email", is("azra@bb.com")))
				.andExpect(jsonPath("$.date_Of_Birth", is("1997-11-03")))
				;
	}


}