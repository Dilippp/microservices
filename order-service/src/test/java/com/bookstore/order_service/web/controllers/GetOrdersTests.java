package com.bookstore.order_service.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookstore.order_service.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

class GetOrdersTests extends AbstractIntegrationTest {

    @Test
    void shouldGetOrdersSuccessfully() throws Exception {
        mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
    }
}
