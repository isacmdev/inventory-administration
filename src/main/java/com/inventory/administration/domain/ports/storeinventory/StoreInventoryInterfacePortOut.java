package com.inventory.administration.domain.ports.storeinventory;

import com.inventory.administration.domain.entity.storeinventory.StoreInventory;

import java.util.List;
import java.util.Optional;

public interface StoreInventoryInterfacePortOut {
    StoreInventory save(StoreInventory storeInventory);
    List<StoreInventory> findAllByStoreId(Long storeId);
    Optional<StoreInventory> findByStoreIdAndSku(Long storeId, String sku);
    void decrementStock(Long storeId, String sku, int quantity);
}