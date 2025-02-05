package com.jinyili.currencies.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinyili.currencies.services.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class CurrencyControllerTest {

    public MockMvc mockMvc;
    @Autowired
    public  CurrencyController currencyController;


    @MockitoBean
    public CurrencyService currencyService;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void testGetCurrenyList() throws Exception {

        // when then
        mockMvc.perform(get("/api/v1/getCurrencyList"))
                .andExpect(status().is(200));
    }

    @Test
    void testGetRateSuccess() throws Exception{
        String payload = "{\"base\":\"EUR\",\"quote\":\"USD\"}";

        mockMvc.perform(post("/api/v1/getRate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(payload))
                .andExpect(status().is(200));
    }

    @Test
    void testGetRateFailException() throws Exception{
        String payload = "{\"base\":\"EUR\"}";

        mockMvc.perform(post("/api/v1/getRate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(payload))
                .andExpect(status().is(400));
    }

}
