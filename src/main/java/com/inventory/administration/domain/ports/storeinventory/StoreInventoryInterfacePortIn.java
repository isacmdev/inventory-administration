package com.inventory.administration.domain.ports.storeinventory;

import com.inventory.administration.domain.entity.model.StoreWithProducts;
import com.inventory.administration.domain.entity.storeinventory.StoreInventory;


public interface StoreInventoryInterfacePortIn {
    StoreInventory assignStock(StoreInventory storeInventory);
    StoreWithProducts getStoreWithProducts (Long storeId);
    StoreWithProducts getStoreWithProducts();
}
