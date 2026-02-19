package com.inventory.administration.infrastucture.mapper;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.infrastructure.dto.store.StoreRequestDto;
import com.inventory.administration.infrastructure.dto.store.StoreResponseDto;
import com.inventory.administration.infrastructure.mapper.store.StoreMapperDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreMapperDtoTest {
    @Test
    void toDomain_deberiaMapearCorrectamente() {
        StoreRequestDto dto = StoreRequestDto.builder()
                .name("Tienda 1")
                .address("Calle 123")
                .city("CiudadX")
                .build();
        Store store = StoreMapperDto.toDomain(dto);
        assertNull(store.getId());
        assertEquals("Tienda 1", store.getName());
        assertEquals("Calle 123", store.getAddress());
        assertEquals("CiudadX", store.getCity());
    }

    @Test
    void toResponse_deberiaMapearCorrectamente() {
        Store store = Store.builder()
                .id(10)
                .name("Tienda 2")
                .address("Avenida 456")
                .city("CiudadY")
                .build();
        StoreResponseDto dto = StoreMapperDto.toResponse(store);
        assertEquals(10, dto.getId());
        assertEquals("Tienda 2", dto.getName());
        assertEquals("Avenida 456", dto.getAddress());
        assertEquals("CiudadY", dto.getCity());
    }

    @Test
    void toStoreList_deberiaMapearListaCorrectamente() {
        Store store1 = Store.builder().id(1).name("A").address("DirA").city("C1").build();
        Store store2 = Store.builder().id(2).name("B").address("DirB").city("C2").build();
        List<Store> stores = List.of(store1, store2);
        List<StoreResponseDto> dtos = StoreMapperDto.toStoreList(stores);
        assertEquals(2, dtos.size());
        assertEquals(1, dtos.get(0).getId());
        assertEquals("A", dtos.get(0).getName());
        assertEquals("DirA", dtos.get(0).getAddress());
        assertEquals("C1", dtos.get(0).getCity());
        assertEquals(2, dtos.get(1).getId());
        assertEquals("B", dtos.get(1).getName());
        assertEquals("DirB", dtos.get(1).getAddress());
        assertEquals("C2", dtos.get(1).getCity());
    }

    @Test
    void toStoreList_listaVaciaRetornaListaVacia() {
        List<StoreResponseDto> dtos = StoreMapperDto.toStoreList(List.of());
        assertNotNull(dtos);
        assertTrue(dtos.isEmpty());
    }
}
