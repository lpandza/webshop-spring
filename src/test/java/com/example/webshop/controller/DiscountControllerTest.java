package com.example.webshop.controller;

import com.example.webshop.dto.DiscountDto;
import com.example.webshop.facade.DiscountFacade;
import com.example.webshop.form.DiscountForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {
    @MockBean
    private DiscountFacade discountFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getDiscountByCode() throws Exception {
        DiscountDto discountDto = new DiscountDto(1L,"a1", 5D, false);

        when(discountFacade.getByCode("a1")).thenReturn(Optional.of(discountDto));

        mockMvc.perform(get("/api/discount?code=a1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("a1"))
                .andExpect(jsonPath("$.discountPercentage").value(5D))
                .andExpect(jsonPath("$.isUsed").value(false))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void saveDiscount() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DiscountForm discountForm = new DiscountForm("a1", 5D, false);
        DiscountDto discountDto = new DiscountDto(1L,"a1", 5D, false);

        when(discountFacade.save(discountForm)).thenReturn(Optional.of(discountDto));

        mockMvc.perform(post("/api/discount")
                        .with(httpBasic("admin", "pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(discountForm)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("a1"))
                .andExpect(jsonPath("$.discountPercentage").value(5D))
                .andExpect(jsonPath("$.isUsed").value(false))
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
