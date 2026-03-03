package com.inventory.administration.domain.ports.inventory;

import com.inventory.administration.domain.entity.inventory.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryInterfacePortOut {
    Optional<Inventory> findBySku(String sku);
    Inventory save(Inventory inventory);
    Optional<Inventory> findById(Long id);
    Inventory update(Long id, Inventory inventory);
    void deleteById(Long id);
    List<Inventory> findAllBySkuIn(List<String> skus);
    Optional<Inventory> findBySkuForUpdate(String sku);

}
