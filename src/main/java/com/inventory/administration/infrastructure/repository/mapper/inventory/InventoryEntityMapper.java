package com.inventory.administration.infrastructure.repository.mapper.inventory;

import com.inventory.administration.domain.entity.inventory.Inventory;
import com.inventory.administration.infrastructure.repository.entity.inventory.InventoryData;

public class InventoryEntityMapper {

    public static InventoryData toData(Inventory inventory) {
        return InventoryData.builder()
                .id(inventory.getId())
                .sku(inventory.getSku())
                .factoryPrice(inventory.getFactoryPrice())
                .discount(inventory.getDiscount())
                .totalStock(inventory.getTotalStock())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }

    public static Inventory toDomain(InventoryData data) {
        return Inventory.builder()
                .id(data.getId())
                .sku(data.getSku())
                .factoryPrice(data.getFactoryPrice())
                .discount(data.getDiscount())
                .totalStock(data.getTotalStock())
                .createdAt(data.getCreatedAt())
                .updatedAt(data.getUpdatedAt())
                .build();
    }
}
