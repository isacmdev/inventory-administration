package com.inventory.administration.infrastructure.repository.mapper.storeinventory;

import com.inventory.administration.domain.entity.storeinventory.StoreInventory;
import com.inventory.administration.infrastructure.repository.entity.store.StoreData;
import com.inventory.administration.infrastructure.repository.entity.storeinventory.StoreInventoryData;

public class StoreInventoryEntityMapper {
    private StoreInventoryEntityMapper () {}

    public static StoreInventory toDomain(StoreInventoryData data){
        return StoreInventory.builder()
                .id(data.getId())
                .storeId(data.getStore().getId())
                .sku(data.getSku())
                .stock(data.getStock())
                .build();
    }

    public static StoreInventoryData toData(StoreInventory storeInventory){

        StoreData store = new StoreData();
        store.setId(storeInventory.getStoreId());

        return StoreInventoryData.builder()
                .id(storeInventory.getId())
                .store(store)
                .sku(storeInventory.getSku())
                .stock(storeInventory.getStock())
                .build();
    }
}
