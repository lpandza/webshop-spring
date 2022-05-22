package com.example.webshop.controller;


import com.example.webshop.dto.ItemDto;
import com.example.webshop.entity.Brand;
import com.example.webshop.facade.ReportFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {
    @MockBean
    private ReportFacade reportFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBestSellingItem() throws Exception {
        Brand brand = new Brand("newBrand");
        ItemDto itemDto = new ItemDto(1L, "newItem", "Description...", 200D, 5, brand);

        when(reportFacade.getBestSellingItemCurrentWeek()).thenReturn(Optional.of(itemDto));

        mockMvc.perform(get("/api/report/bestSelling")
                        .with(httpBasic("admin", "pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("newItem"))
                .andExpect(jsonPath("$.description").value("Description..."))
                .andExpect(jsonPath("$.price").value(200))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.brand").isNotEmpty())
                .andExpect(status().isOk());
    }
}
