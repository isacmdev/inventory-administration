package com.inventory.administration.infrastucture.repository.mapper;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.infrastructure.repository.entity.store.StoreData;
import com.inventory.administration.infrastructure.repository.mapper.store.StoreEntityMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoreEntityMapperTest {
    @Test
    void toDomain_deberiaMapearCorrectamente() {
        StoreData data = StoreData.builder()
                .id(1)
                .name("Tienda Test")
                .address("Calle Test 123")
                .city("CiudadTest")
                .build();
        Store store = StoreEntityMapper.toDomain(data);
        assertNotNull(store);
        assertEquals(1, store.getId());
        assertEquals("Tienda Test", store.getName());
        assertEquals("Calle Test 123", store.getAddress());
        assertEquals("CiudadTest", store.getCity());
    }

    @Test
    void toData_deberiaMapearCorrectamente() {
        Store store = Store.builder()
                .id(2)
                .name("Tienda Data")
                .address("Avenida Data 456")
                .city("CiudadData")
                .build();
        StoreData data = StoreEntityMapper.toData(store);
        assertNotNull(data);
        assertEquals(2, data.getId());
        assertEquals("Tienda Data", data.getName());
        assertEquals("Avenida Data 456", data.getAddress());
        assertEquals("CiudadData", data.getCity());
    }

    @Test
    void toDomain_conNull_retornaNullPointerException() {
        assertThrows(NullPointerException.class, () -> StoreEntityMapper.toDomain(null));
    }

    @Test
    void toData_conNull_retornaNullPointerException() {
        assertThrows(NullPointerException.class, () -> StoreEntityMapper.toData(null));
    }
}
