package com.inventory.administration.infrastucture.repository.repository;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.infrastructure.repository.entity.store.StoreData;
import com.inventory.administration.infrastructure.repository.mapper.store.StoreEntityMapper;
import com.inventory.administration.infrastructure.repository.repository.store.StoreJpaRepository;
import com.inventory.administration.infrastructure.repository.repository.store.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StoreRepositoryTest {
    @Mock
    private StoreJpaRepository storeJpaRepository;

    @InjectMocks
    private StoreRepository storeRespository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_deberiaGuardarYRetornarStore() {
        Store store = Store.builder().name("Tienda").address("Dir").city("Ciudad").build();
        StoreData storeData = StoreEntityMapper.toData(store);
        StoreData savedData = StoreData.builder().id(1).name("Tienda").address("Dir").city("Ciudad").build();
        when(storeJpaRepository.save(any(StoreData.class))).thenReturn(savedData);
        Store result = storeRespository.save(store);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Tienda", result.getName());
        verify(storeJpaRepository).save(any(StoreData.class));
    }

    @Test
    void save_lanzaExcepcionSiJpaFalla() {
        Store store = Store.builder().name("Tienda").address("Dir").city("Ciudad").build();
        when(storeJpaRepository.save(any(StoreData.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> storeRespository.save(store));
    }

    @Test
    void update_deberiaActualizarYRetornarStore() {
        Integer id = 1;
        Store store = Store.builder().name("Nueva").address("NuevaDir").city("NuevaCiudad").build();
        StoreData existente = StoreData.builder().id(id).name("Vieja").address("ViejaDir").city("ViejaCiudad").build();
        StoreData actualizado = StoreData.builder().id(id).name("Nueva").address("NuevaDir").city("NuevaCiudad").build();
        when(storeJpaRepository.findById(id)).thenReturn(Optional.of(existente));
        when(storeJpaRepository.save(any(StoreData.class))).thenReturn(actualizado);
        Store result = storeRespository.update(id, store);
        assertNotNull(result);
        assertEquals("Nueva", result.getName());
        assertEquals("NuevaDir", result.getAddress());
        assertEquals("NuevaCiudad", result.getCity());
        verify(storeJpaRepository).findById(id);
        verify(storeJpaRepository).save(any(StoreData.class));
    }

    @Test
    void update_lanzaExcepcionSiNoExiste() {
        Integer id = 99;
        Store store = Store.builder().name("Nueva").address("NuevaDir").city("NuevaCiudad").build();
        when(storeJpaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> storeRespository.update(id, store));
    }

    @Test
    void findAll_deberiaRetornarListaDeStores() {
        StoreData data1 = StoreData.builder().id(1).name("A").address("DirA").city("C1").build();
        StoreData data2 = StoreData.builder().id(2).name("B").address("DirB").city("C2").build();
        when(storeJpaRepository.findAll()).thenReturn(List.of(data1, data2));
        List<Store> stores = storeRespository.findAll();
        assertEquals(2, stores.size());
        assertEquals("A", stores.get(0).getName());
        assertEquals("B", stores.get(1).getName());
        verify(storeJpaRepository).findAll();
    }

    @Test
    void findAll_lanzaExcepcionSiJpaFalla() {
        when(storeJpaRepository.findAll()).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> storeRespository.findAll());
    }

    @Test
    void findById_deberiaRetornarStoreSiExiste() {
        Integer id = 1;
        StoreData data = StoreData.builder().id(id).name("A").address("DirA").city("C1").build();
        when(storeJpaRepository.findById(id)).thenReturn(Optional.of(data));
        Store store = storeRespository.findById(id);
        assertNotNull(store);
        assertEquals("A", store.getName());
        verify(storeJpaRepository).findById(id);
    }

    @Test
    void findById_lanzaExcepcionSiNoExiste() {
        Integer id = 99;
        when(storeJpaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> storeRespository.findById(id));
    }

    @Test
    void deleteById_deberiaLlamarJpaDelete() {
        Integer id = 1;
        doNothing().when(storeJpaRepository).deleteById(id);
        storeRespository.deleteById(id);
        verify(storeJpaRepository).deleteById(id);
    }

    @Test
    void deleteById_lanzaExcepcionSiJpaFalla() {
        Integer id = 1;
        doThrow(new RuntimeException("DB error")).when(storeJpaRepository).deleteById(id);
        assertThrows(RuntimeException.class, () -> storeRespository.deleteById(id));
    }
}
