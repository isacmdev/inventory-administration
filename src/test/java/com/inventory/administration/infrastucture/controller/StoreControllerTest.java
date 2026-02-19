package com.inventory.administration.infrastucture.controller;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.domain.ports.store.StoreInterfacePortIn;
import com.inventory.administration.infrastructure.controller.store.StoreController;
import com.inventory.administration.infrastructure.dto.store.StoreRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StoreControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private StoreInterfacePortIn storeInterfacePortIn;

    @InjectMocks
    private StoreController storeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
    }

    @Test
    void addStore_deberiaRetornarStoreResponseDto() throws Exception {
        StoreRequestDto requestDto = StoreRequestDto.builder().name("Tienda").address("Dir").city("Ciudad").build();
        Store store = Store.builder().id(1).name("Tienda").address("Dir").city("Ciudad").build();
        when(storeInterfacePortIn.addStore(any(Store.class))).thenReturn(store);
        mockMvc.perform(post("/stores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tienda"));
    }

    @Test
    void updateStore_deberiaRetornarStoreResponseDto() throws Exception {
        StoreRequestDto requestDto = StoreRequestDto.builder().name("Tienda").address("Dir").city("Ciudad").build();
        Store store = Store.builder().id(1).name("Tienda").address("Dir").city("Ciudad").build();
        when(storeInterfacePortIn.updateStore(eq(1), any(Store.class))).thenReturn(store);
        mockMvc.perform(put("/stores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tienda"));
    }

    @Test
    void getAllStores_deberiaRetornarListaDeStoreResponseDto() throws Exception {
        Store store = Store.builder().id(1).name("Tienda").address("Dir").city("Ciudad").build();
        when(storeInterfacePortIn.getAllStores()).thenReturn(List.of(store));
        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Tienda"));
    }

    @Test
    void getStoreById_deberiaRetornarStoreResponseDto() throws Exception {
        Store store = Store.builder().id(1).name("Tienda").address("Dir").city("Ciudad").build();
        when(storeInterfacePortIn.getStoreById(1)).thenReturn(store);
        mockMvc.perform(get("/stores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tienda"));
    }

    @Test
    void deleteStore_deberiaRetornarNoContent() throws Exception {
        doNothing().when(storeInterfacePortIn).deleteStore(1);
        mockMvc.perform(delete("/stores/1"))
                .andExpect(status().isNoContent());
    }
}
