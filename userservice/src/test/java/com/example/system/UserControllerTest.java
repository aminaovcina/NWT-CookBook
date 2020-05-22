package com.example.system;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.system.models.Gender;
import com.example.system.models.User;
import com.example.system.repositories.UserRepository;
import com.example.system.services.UserService;
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
            return m.writeValueAsString(new User("azra", "ibric",  Gender.Zensko, null, "sarajevo", "azara@bb.com"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
	}
	public String convertUserToJsonTheSmeEmail() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new User("azra", "ibric",  Gender.Zensko, null, "sarajevo", "azara@bb.com"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
	}
	public String convertUserToJsonUpdate() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new User("azra", "ibric",  Gender.Zensko, null, "sarajevo", "azzzz@bb.com"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
	}
	
	//test 2 i 3 padaju nakon drugog pokretanja, jer email ne moze biti isti, dok kada brise
	//usera, on je prvi put tu, a drugi put je obrisan, pa nema sta brisati...
	@Test
    @Order(2)
    public void saveUser() throws Exception {
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
	
	@Test
	@Order(4)
	public void updateUser() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.put("/user/update/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertUserToJsonUpdate()))
        .andExpect(status().isOk())
        .andReturn();

        res = r.getResponse().getContentAsString();
        convertResToUser();
	}

	@Test
	@Order(5)
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
	//testiranje apija za komunikaciju izmedju recipe servisa i user servisa
	@Test
	@Order(6)
	public void getUsersRecipe() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user_recipes/5")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(5)))
				.andExpect(jsonPath("$.firstName", is("azz")))
				.andExpect(jsonPath("$.lastName", is("ibr")))
				.andExpect(jsonPath("$.gender", is("female")))
				.andExpect(jsonPath("$.city", is("saa")))
				.andExpect(jsonPath("$.email", is("azzzz@bb.com")))
				.andExpect(jsonPath("$.active", is(true)))
				
				.andExpect(jsonPath("$.recipes", hasSize(2)))
				.andExpect(jsonPath("$.recipes[0].id", is(6)))
				.andExpect(jsonPath("$.recipes[0].title", is("Klepe")))
				.andExpect(jsonPath("$.recipes[0].description", is("Klepice slatke male")));
	}

/// error tests
	@Test
	@Order(7)
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
	@Order(8)
	public void shouldShow404() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/user/125")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertUserToJson()))
		.andExpect(status().isNotFound())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToUser();
	}

	@Test
	@Order(9)
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
	@Order(10)
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
    @Order(11)
    public void TheSameEmailError() throws Exception {
        MvcResult r = mvc.perform(MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
				.content(convertUserToJsonTheSmeEmail()))
				.andExpect(status().isBadRequest())
                .andReturn();
        res = r.getResponse().getContentAsString();
        convertResToUser();
	}
}