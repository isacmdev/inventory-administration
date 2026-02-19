package com.inventory.administration.domain.ports.inventory;

import com.inventory.administration.domain.entity.inventory.Inventory;


public interface InventoryInterfacePortIn {
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Long id, Inventory inventory);
    void deleteInventory(Long id);
}
