package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.example.demo.errors.exception.FavoriteAlreadyExistsException;
import com.example.demo.models.Favorites;
import com.example.demo.models.Subscription;
import com.example.demo.repositories.FavoritesRepository;
import com.example.demo.repositories.SubscriptionRepository;
import com.example.demo.services.FavoritesService;
import com.example.demo.services.SubscriptionService;
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
public class SubscriptionsControllerTests {
    @Autowired
    private MockMvc mmvc;
    private SubscriptionRepository subscriptionRepository;
    private SubscriptionService subscriptionService;
    String res;
    Subscription newSubscription;

    public void convertResToSubscription() {
        ObjectMapper m = new ObjectMapper();

        try {
            newSubscription = m.readValue(res, Subscription.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String convertSubscriptionToJson() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(new Subscription(5, 6));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    @Order(1)
    public void getAllSubscriptions() throws Exception {
        mmvc.perform(MockMvcRequestBuilders.get("/subs/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idUser", is(2)))
                .andExpect(jsonPath("$[0].idSubUser", is(1)))
                .andExpect(jsonPath("$[1].idUser", is(5)))
                .andExpect(jsonPath("$[1].idSubUser", is(1)));
    }

    @Test
    @Order(2)
    public void getSubscriptionById() throws Exception {
        mmvc.perform(MockMvcRequestBuilders.get("/subs/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.idUser", is(2)))
                .andExpect(jsonPath("$.idSubUser", is(1)));
    }

    @Test
    @Order(3)
    public void getSubscriptionsByUser() throws Exception {
        mmvc.perform(MockMvcRequestBuilders.get("/subs/user/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].idSubUser", is(1)))
                .andExpect(jsonPath("$[0].idUser", is(2)));
    }

    @Test
    @Order(4)
    public void saveSubscription() throws Exception {
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.post("/subs/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertSubscriptionToJson()))
                .andExpect(status().isOk())
                .andReturn();
        res = response.getResponse().getContentAsString();
        convertResToSubscription();
    }

    @Test
    @Order(5)
    public void deleteSubscription() throws Exception {
        /*
          List<Subscription> lista = subscriptionService.getAllSubscriptions();
          int size = lista.size(); 
          int id = lista.get(size - 1).getId();
    */  
        String id = Integer.toString(newSubscription.getId());
        mmvc.perform(delete("/subs/delete/"+id))
                    .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void getSubscriptionByUser_400_Greska() throws Exception {
 
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.get("/subs/user/200")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertSubscriptionToJson()))
                .andExpect(status().isBadRequest())
                .andReturn();
        res = response.getResponse().getContentAsString();
        convertResToSubscription();
    }

    @Test
    @Order(7)
    public void getSubscriptionById_400_Greska() throws Exception {
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.get("/subs/j")
        .contentType(MediaType.APPLICATION_JSON)
                .content(convertSubscriptionToJson()))
                .andExpect(status().isBadRequest())
                .andReturn();
        res = response.getResponse().getContentAsString();
        convertResToSubscription();
    }

    @Test
    @Order(8)
    public void saveSubscription_Conflict_Greska() throws Exception {
        MvcResult response = mmvc.perform(MockMvcRequestBuilders.post("/subs/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertSubscriptionToJson()))
                .andExpect(status().isConflict())
                .andReturn();
        res = response.getResponse().getContentAsString();
        convertResToSubscription();
    }

    @Test
    @Order(9)
    public void deleteSubscription_404_Greska() throws Exception {
        List<Subscription> lista = subscriptionRepository.findAll();
        int size=lista.size();
        int id= lista.get(size-1).getId()+1;
        mmvc.perform(MockMvcRequestBuilders.delete("/subs/delete/" + Integer.toString(id)))
                                            .andExpect(status().isNotFound());
    }
}