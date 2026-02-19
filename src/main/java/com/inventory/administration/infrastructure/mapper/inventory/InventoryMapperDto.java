package com.inventory.administration.infrastructure.mapper.inventory;

import com.inventory.administration.domain.entity.inventory.Inventory;
import com.inventory.administration.infrastructure.dto.inventory.InventoryRequestDto;
import com.inventory.administration.infrastructure.dto.inventory.InventoryResponseDto;

import java.util.List;

public class InventoryMapperDto {

    public static Inventory toDomain(InventoryRequestDto dto) {
        return Inventory.builder()
                .sku(dto.getSku())
                .factoryPrice(dto.getFactoryPrice())
                .discount(dto.getDiscount())
                .totalStock(dto.getTotalStock())
                .build();
    }

    public static InventoryResponseDto toResponse(Inventory inventory) {
        return InventoryResponseDto.builder()
                .id(inventory.getId())
                .sku(inventory.getSku())
                .factoryPrice(inventory.getFactoryPrice())
                .discount(inventory.getDiscount())
                .totalStock(inventory.getTotalStock())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }

    public static List<InventoryResponseDto> toResponseList(List<Inventory> inventories) {
        return inventories.stream()
                .map(InventoryMapperDto::toResponse)
                .toList();
    }
}
