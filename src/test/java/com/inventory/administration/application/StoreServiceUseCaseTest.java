package com.inventory.administration.application;

import com.inventory.administration.application.store.StoreServiceUseCase;
import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.domain.ports.store.StoreInterfacePortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class StoreServiceUseCaseTest {
    @Mock
    private StoreInterfacePortOut storeInterfacePortOut;

    @InjectMocks
    private StoreServiceUseCase storeServiceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStore_deberiaDelegarEnPortYRetornarStore() {
        Store store = Store.builder().name("Tienda").address("Dir").city("Ciudad").build();
        Store saved = Store.builder().id(1).name("Tienda").address("Dir").city("Ciudad").build();
        when(storeInterfacePortOut.save(store)).thenReturn(saved);
        Store result = storeServiceUseCase.addStore(store);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(storeInterfacePortOut).save(store);
    }

    @Test
    void updateStore_deberiaActualizarYRetornarStore() {
        Long id = 1;
        Store original = Store.builder().id(id).name("Vieja").address("ViejaDir").city("ViejaCiudad").build();
        Store update = Store.builder().name("Nueva").address("NuevaDir").city("NuevaCiudad").build();
        Store updated = Store.builder().id(id).name("Nueva").address("NuevaDir").city("NuevaCiudad").build();
        when(storeInterfacePortOut.findById(id)).thenReturn(original);
        when(storeInterfacePortOut.update(eq(id), any(Store.class))).thenReturn(updated);
        Store result = storeServiceUseCase.updateStore(id, update);
        assertNotNull(result);
        assertEquals("Nueva", result.getName());
        assertEquals("NuevaDir", result.getAddress());
        assertEquals("NuevaCiudad", result.getCity());
        verify(storeInterfacePortOut).findById(id);
        verify(storeInterfacePortOut).update(eq(id), any(Store.class));
    }

    @Test
    void getAllStores_deberiaRetornarListaDeStores() {
        Store s1 = Store.builder().id(1).name("A").address("DirA").city("C1").build();
        Store s2 = Store.builder().id(2).name("B").address("DirB").city("C2").build();
        when(storeInterfacePortOut.findAll()).thenReturn(List.of(s1, s2));
        List<Store> stores = storeServiceUseCase.getAllStores();
        assertEquals(2, stores.size());
        assertEquals("A", stores.get(0).getName());
        assertEquals("B", stores.get(1).getName());
        verify(storeInterfacePortOut).findAll();
    }

    @Test
    void getStoreById_deberiaRetornarStoreSiIdValido() {
        Long id = 1;
        Store store = Store.builder().id(id).name("A").address("DirA").city("C1").build();
        when(storeInterfacePortOut.findById(id)).thenReturn(store);
        Store result = storeServiceUseCase.getStoreById(id);
        assertNotNull(result);
        assertEquals("A", result.getName());
        verify(storeInterfacePortOut).findById(id);
    }

    @Test
    void getStoreById_lanzaExcepcionSiIdNegativo() {
        assertThrows(IllegalArgumentException.class, () -> storeServiceUseCase.getStoreById(0));
        assertThrows(IllegalArgumentException.class, () -> storeServiceUseCase.getStoreById(-1));
    }

    @Test
    void deleteStore_deberiaEliminarSiIdValido() {
        Integer id = 1;
        Store store = Store.builder().id(id).name("A").address("DirA").city("C1").build();
        when(storeInterfacePortOut.findById(id)).thenReturn(store);
        doNothing().when(storeInterfacePortOut).deleteById(id);
        storeServiceUseCase.deleteStore(id);
        verify(storeInterfacePortOut).findById(id);
        verify(storeInterfacePortOut).deleteById(id);
    }

    @Test
    void deleteStore_lanzaExcepcionSiIdNegativo() {
        assertThrows(IllegalArgumentException.class, () -> storeServiceUseCase.deleteStore(0));
        assertThrows(IllegalArgumentException.class, () -> storeServiceUseCase.deleteStore(-5));
    }
}

