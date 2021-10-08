package com.github.sharifulineugene.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sharifulineugene.config.SpringConfig;
import com.github.sharifulineugene.dto.Balance;
import com.github.sharifulineugene.dto.CardDto;
import com.github.sharifulineugene.entity.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class AccountControllerTest{
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnAllAccounts() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[2].id").value(3));

    }

    @Test
    public void shouldReturnAccountById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnAllCardsFromAccount() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/accounts/1/cards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void shouldReturnAccountBalance() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/accounts/1/balance"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.balance").value(1L));
    }

    @Test
    public void shouldReturnRedirectionByNewCardByIdAccount() throws Exception{
        CardDto card = new CardDto();
        card.setCardNumber("0000000000000028");
        card.setExpDate("11/24");
        card.setStatus(Card.Status.NEW);

        mvc.perform(MockMvcRequestBuilders.post("/accounts/1/newcard")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void shouldReturnRedirectionByChangedBalance() throws Exception{
        Balance balance = new Balance();
        balance.setBalance(100);
        mvc.perform(MockMvcRequestBuilders.put("/accounts/1")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(balance)))
                .andExpect(status().is3xxRedirection());

    }


}
