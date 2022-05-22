package com.example.webshop.controller;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.PageDto;
import com.example.webshop.entity.Brand;
import com.example.webshop.entity.Item;
import com.example.webshop.facade.ItemFacade;
import com.example.webshop.form.ItemFilterForm;
import com.example.webshop.form.ItemForm;
import com.example.webshop.specification.ItemPageSettings;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {
    @MockBean
    private ItemFacade itemFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getItems() throws Exception {
        Brand brand = new Brand("newBrand");
        ItemDto itemDto = new ItemDto(1L, "newItem", "itemDesctiption", 300D, 2, brand);
        PageDto<ItemDto> pageDto = new PageDto<>(List.of(itemDto), 1, 1);

        when(itemFacade.getAll(new ItemPageSettings(), new ItemFilterForm()))
                .thenReturn(pageDto);

        mockMvc.perform(get("/api/item"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").isNotEmpty())
                .andExpect(jsonPath("$.content[0].name").isNotEmpty())
                .andExpect(jsonPath("$.content[0].price").isNotEmpty())
                .andExpect(jsonPath("$.content[0].description").isNotEmpty())
                .andExpect(jsonPath("$.content[0].quantity").isNotEmpty())
                .andExpect(jsonPath("$.content[0].brand").isNotEmpty())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getItemById() throws Exception {
        Brand brand = new Brand("newBrand");
        ItemDto itemDto = new ItemDto(1L, "newItem", "itemDescription", 300D, 2, brand);

        when(itemFacade.getById(1L)).thenReturn(Optional.of(itemDto));

        mockMvc.perform(get("/api/item/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.price").isNotEmpty())
                .andExpect(jsonPath("$.description").isNotEmpty())
                .andExpect(jsonPath("$.quantity").isNotEmpty())
                .andExpect(jsonPath("$.brand").isNotEmpty())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getItemById_whenIdWrong() throws Exception {
        Brand brand = new Brand("newBrand");
        ItemDto itemDto = new ItemDto(1L, "newItem", "itemDescription", 300D, 2, brand);

        when(itemFacade.getById(1L)).thenReturn(Optional.of(itemDto));

        mockMvc.perform(get("/api/item/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void newItem() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Brand brand = new Brand("newBrand");
        ItemForm itemForm = new ItemForm("newItem", "Description...", 200D, 5,brand);
        ItemDto itemDto = new ItemDto(1L, "newItem", "Description...", 200D, 5, brand);

        when(itemFacade.save(any(ItemForm.class))).thenReturn(Optional.of(itemDto));

        mockMvc.perform(post("/api/item")
                        .with(httpBasic("admin", "pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemForm)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("newItem"))
                .andExpect(jsonPath("$.description").value("Description..."))
                .andExpect(jsonPath("$.price").value(200))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.brand").isNotEmpty())
                .andExpect(status().isCreated());
    }

    @Test
    void newItem_whenNameEmpty() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Brand brand = new Brand("newBrand");
        ItemForm itemForm = new ItemForm("", "Description...", 200D, 5,brand);
        ItemDto itemDto = new ItemDto(1L, "", "Description...", 200D, 5, brand);

        when(itemFacade.save(any(ItemForm.class))).thenReturn(Optional.of(itemDto));

        mockMvc.perform(post("/api/item")
                        .with(httpBasic("admin", "pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemForm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteItem() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Brand brand = new Brand("newBrand");
        ItemDto itemDto = new ItemDto(1L, "", "Description...", 200D, 5, brand);

        when(itemFacade.deleteById(1L)).thenReturn(Optional.of(itemDto));

        mockMvc.perform(delete("/api/item/1")
                        .with(httpBasic("admin", "pass"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void deleteItem_whenIdWrong() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Brand brand = new Brand("newBrand");
        ItemDto itemDto = new ItemDto(1L, "", "Description...", 200D, 5, brand);

        when(itemFacade.deleteById(1L)).thenReturn(Optional.of(itemDto));

        mockMvc.perform(delete("/api/item/2")
                        .with(httpBasic("admin", "pass"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void updateItem() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Brand brand = new Brand("newBrand");
        Item item = new Item(1L, "itemName", "desc", 550D, 15, brand);
        ItemForm itemForm = new ItemForm("newName", "desc", 800D, 15, brand);
        ItemDto itemDto = new ItemDto(1L,"newName", "desc", 800D, 15, brand);

        when(itemFacade.update(1L, itemForm)).thenReturn(Optional.of(itemDto));

        mockMvc.perform(put("/api/item/1")
                        .with(httpBasic("admin", "pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemForm)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("newName"))
                .andExpect(jsonPath("$.description").value("desc"))
                .andExpect(jsonPath("$.price").value(800D))
                .andExpect(jsonPath("$.quantity").value(15))
                .andExpect(jsonPath("$.brand").isNotEmpty())
                .andExpect(status().isOk());
    }


}
