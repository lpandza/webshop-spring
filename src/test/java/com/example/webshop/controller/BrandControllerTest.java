package com.example.webshop.controller;

import com.example.webshop.dto.BrandDto;
import com.example.webshop.facade.BrandFacade;
import com.example.webshop.form.BrandForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BrandController.class)
public class BrandControllerTest {
    @MockBean
    private BrandFacade brandFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBrands() throws Exception {
        BrandDto brandDTO = new BrandDto(1L,"newBrand");

        when(brandFacade.getAll()).thenReturn(List.of(brandDTO));

        mockMvc.perform(get("/api/brand"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getBrandById() throws Exception {
        BrandDto brandDTO = new BrandDto(1L,"newBrand");

        when(brandFacade.getById(1L)).thenReturn(Optional.of(brandDTO));

        mockMvc.perform(get("/api/brand?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("newBrand"));
    }

    @Test
    void getBrandById_whenIdWrong() throws Exception {
        mockMvc.perform(get("/api/brand?id=2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void newBrand() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        BrandForm brandForm = new BrandForm("newBrand");
        BrandDto brandDTO = new BrandDto(1L,"newBrand");

        when(brandFacade.save(any(BrandForm.class))).thenReturn(Optional.of(brandDTO));

        mockMvc.perform(post("/api/brand")
                        .with(httpBasic("admin", "pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandForm)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("newBrand"))
                .andExpect(status().isCreated());
    }

    @Test
    void newBrand_whenNameNotGiven() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        BrandForm brandForm = new BrandForm("");

        mockMvc.perform(post("/api/brand")
                        .with(httpBasic("admin", "pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandForm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteBrand() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        BrandDto brandDTO = new BrandDto(1L,"newBrand");

        when(brandFacade.deleteById(1L)).thenReturn(Optional.of(brandDTO));

        mockMvc.perform(delete("/api/brand/1")
                        .with(httpBasic("admin", "pass"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void deleteBrand_whenIdWrong() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        BrandDto brandDTO = new BrandDto(1L,"newBrand");

        when(brandFacade.deleteById(1L)).thenReturn(Optional.of(brandDTO));

        mockMvc.perform(delete("/api/brand/2")
                        .with(httpBasic("admin", "pass"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }
}
