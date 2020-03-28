package com.example.demo;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.models.Account;
import com.example.demo.models.Gender;
import com.example.demo.models.User;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AccountService;
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
	public String convertAccountToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            User user = new User("azra", "ibric",  Gender.female, null, "sarajevo", "azara@bb.com");
            return m.writeValueAsString(new Account(user,"azraIbric", "azraibric"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

	@Test
    public void saveOrUpdateUser() throws Exception {
        MvcResult r = mvc.perform(MockMvcRequestBuilders.post("/update/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertAccountToJson()))
                .andExpect(status().isOk())
                .andReturn();

        res = r.getResponse().getContentAsString();
        convertResToAccount();
	}

	


}