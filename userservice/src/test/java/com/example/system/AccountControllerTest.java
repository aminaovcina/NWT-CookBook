package com.example.system;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.system.models.Account;
import com.example.system.models.Gender;
import com.example.system.models.User;
import com.example.system.repositories.AccountRepository;
import com.example.system.repositories.UserRepository;
import com.example.system.services.AccountService;
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
public class AccountControllerTest {
	@Mock
	AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private MockMvc mvc;
    
    String res;
    Account newAccount;

    
    public void convertResToAccount() {
        ObjectMapper m = new ObjectMapper();

        try {
            newAccount = m.readValue(res, Account.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    //za post metodu
	public String convertAccountToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            User user = new User("azraaa", "ibriccc",  Gender.Zensko, null, "sarajevo", "azgaraaa@bb.com");
            return m.writeValueAsString(new Account(user,"azraIbriiigc"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
    //za put metodu
    public String convertAccountToJsonUpdate() {
        ObjectMapper m = new ObjectMapper();
        try {
            User user = new User("anicaaa", "anic",  Gender.Zensko, null, "sarajevo", "anaaa@bb.com");
            return m.writeValueAsString(new Account(user,"aniiic"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    //test 2 i 3 padaju nakon drugog pokretanja, jer username ne moze biti isti, dok kada brise
	//account, on je prvi put tu, a drugi put je obrisan, pa nema sta brisati...
    @Test
    @Order(1)
    public void saveAccount() throws Exception {
        MvcResult r = mvc.perform(MockMvcRequestBuilders.post("/account/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertAccountToJson()))
                .andExpect(status().isOk())
                .andReturn();

        res = r.getResponse().getContentAsString();
        convertResToAccount();
    }
    
    @Test
    @Order(2)
	public void deleteOneAccount() throws Exception {
		accountService.delete(3);
		assertThat(accountRepository.count()).isEqualTo(1);
    }
    
    @Test 
    @Order(3)
	public void getAllAccounts() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/accounts")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].username", is("azraibric")))
				.andExpect(jsonPath("$[0].password", is("azra1234")))
				;
	}

    @Test
    @Order(4)
	public void getOneAccount() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/account/1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.username", is("azraibric")))
				.andExpect(jsonPath("$.password", is("azra1234")))
				;
	}

    @Test
	@Order(5)
	public void updateAccount() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.put("/account/update/15")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertAccountToJsonUpdate()))
        .andExpect(status().isOk())
        .andReturn();

        res = r.getResponse().getContentAsString();
        convertResToAccount();
	}


    //error test

    @Test
	@Order(6)
	public void shouldShow404() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/account/;")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertAccountToJson()))
		.andExpect(status().isNotFound())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToAccount();
	}

	@Test
	@Order(7)
	public void shouldShow400Delete() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.delete("/account/delete/k")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertAccountToJson()))
		.andExpect(status().isBadRequest())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToAccount();
	}

	@Test
	@Order(8)
	public void MethodNotAllowed() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/account/delete/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertAccountToJson()))
		.andExpect(status().isMethodNotAllowed())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToAccount();
    }
    
    @Test
	@Order(9)
	public void shouldShow400Update() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.put("/account/update/k")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertAccountToJson()))
		.andExpect(status().isBadRequest())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToAccount();
    }
    
    @Test
	@Order(10)
	public void shouldShowExceptionTheSameEmail() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.put("/account/update/100")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertAccountToJson()))
		.andExpect(status().isNotFound())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToAccount();
    }
    
    
	@Test
    @Order(11)
    public void TheSameEmailError() throws Exception {
        MvcResult r = mvc.perform(MockMvcRequestBuilders.post("/account/save")
                .contentType(MediaType.APPLICATION_JSON)
				.content("{'username': 'azraibric'}"))
				.andExpect(status().isBadRequest())
                .andReturn();
        res = r.getResponse().getContentAsString();
        convertResToAccount();
	}
}