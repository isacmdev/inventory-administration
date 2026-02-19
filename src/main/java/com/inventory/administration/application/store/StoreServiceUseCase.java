package com.inventory.administration.application.store;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.domain.ports.store.StoreInterfacePortIn;
import com.inventory.administration.domain.ports.store.StoreInterfacePortOut;
import com.inventory.administration.infrastructure.dto.store.changepassword.PasswordChangeRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceUseCase implements StoreInterfacePortIn {

    private final StoreInterfacePortOut storeInterfacePortOut;
    private final PasswordEncoder passwordEncoder;

    public Store addStore(Store store) {
        store.setPassword(passwordEncoder.encode(store.getPassword()));
        return storeInterfacePortOut.save(store);
    }

    public Store updateStore(Long id, Store store) {
        Store existing = storeInterfacePortOut.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

        existing.setName(store.getName());
        existing.setAddress(store.getAddress());
        existing.setCity(store.getCity());
        existing.setEmail(store.getEmail());

        return storeInterfacePortOut.update(id, existing);
    }


    public List<Store> getAllStores() {
        return storeInterfacePortOut.findAll();
    }


    public Store getStoreById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id de la tienda no puede ser negativo");
        }
        return storeInterfacePortOut.findById(id).
                orElseThrow(() -> new RuntimeException("Tienda no encontrada con id " + id));
    }

    public void deleteStore(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id de la tienda no puede ser negativo");
        }
        Store store = storeInterfacePortOut.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id " + id));

        storeInterfacePortOut.deleteById(store.getId());
    }

    @Override
    public String changePassword(Long id, PasswordChangeRequestDto dto) {
        Store store = storeInterfacePortOut.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id " + id));

        if (!passwordEncoder.matches(dto.getCurrentPassword(), store.getPassword())) {
            throw new IllegalArgumentException("La contraseña actual es incorrecta");
        }

        store.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        storeInterfacePortOut.update(id, store);

        return "Contraseña actualizada correctamente";
    }
}
