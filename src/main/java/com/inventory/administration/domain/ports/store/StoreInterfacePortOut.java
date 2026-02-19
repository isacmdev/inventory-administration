package com.inventory.administration.domain.ports.store;

import com.inventory.administration.domain.entity.store.Store;

import java.util.List;
import java.util.Optional;

public interface StoreInterfacePortOut {
    Store save(Store store);
    List<Store> findAll();
    Optional<Store> findById(Long id);
    Store update(Long id, Store store);
    void deleteById(Long id);
}