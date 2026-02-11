package com.bnppf.bookShoppingCart.controller;


import com.bnppf.bookShoppingCart.service.BookShoppingCartPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShoppingCartController.class)
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookShoppingCartPriceService bookShoppingCartPriceService;

    @Test
    void shouldReturnTotalWhenRequestIsValid() throws Exception {

        String requestJson = """
                {
                  "books": ["CLEAN_CODE", "CLEAN_CODER"]
                }
                """;

        when(bookShoppingCartPriceService.calculateTotalPrice(org.mockito.ArgumentMatchers.anyList()))
                .thenReturn(95.0);

        mockMvc.perform(post("/api/bookprice/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("95.0"));
    }

    @Test
    void shouldReturn400WhenInvalidEnumProvided() throws Exception {

        String invalidJson = """
                {
                  "books": ["CLEA1_CODE"]
                }
                """;

        mockMvc.perform(post("/api/bookprice/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Invalid Enum Value"))
                .andExpect(jsonPath("$.message")
                        .value(containsString("Invalid book name")));
    }
}

