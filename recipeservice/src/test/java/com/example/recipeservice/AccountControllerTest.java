package com.example.recipeservice;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.recipeservice.models.Account;
import com.example.recipeservice.repositories.AccountInterface;
import com.example.recipeservice.services.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Mock
	AccountService accountService;
	@Autowired
	private AccountInterface accountRepository;
    @Autowired
    private MockMvc mvc;

    String res;
    Account newUser;

    public void convertResToUser() {
        ObjectMapper m = new ObjectMapper();
        try {
            newUser = m.readValue(res, Account.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public String convertUserToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new Account());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
	}
    @Test
    @Order(1)
    public void getAccountById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/account/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))            
        ;
    }
    @Test
	@Order(2)
	public void shouldShow404() throws Exception {
		MvcResult r = mvc.perform(MockMvcRequestBuilders.get("/account/;")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertUserToJson()))
		.andExpect(status().isNotFound())
		.andReturn();
		res = r.getResponse().getContentAsString();
        convertResToUser();
	}

}