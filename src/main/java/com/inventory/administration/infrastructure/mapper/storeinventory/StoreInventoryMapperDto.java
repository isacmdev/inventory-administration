package com.inventory.administration.infrastructure.mapper.storeinventory;

import com.inventory.administration.domain.entity.storeinventory.StoreInventory;
import com.inventory.administration.infrastructure.dto.storeinventory.storeinventory.StoreInventoryRequestDto;
import com.inventory.administration.infrastructure.dto.storeinventory.storeinventory.StoreInventoryResponseDto;

import java.util.List;

public class StoreInventoryMapperDto {
    private StoreInventoryMapperDto() {}

    public static StoreInventory toDomain (StoreInventoryRequestDto requestDto) {
        return StoreInventory.builder()
                .storeId(requestDto.getStoreId())
                .sku(requestDto.getSku())
                .stock(requestDto.getStock())
                .build();
    }

    public static StoreInventoryResponseDto toResponse (StoreInventory storeInventory) {
        return StoreInventoryResponseDto.builder()
                .id(storeInventory.getId())
                .storeId(storeInventory.getStoreId())
                .sku(storeInventory.getSku())
                .stock(storeInventory.getStock())
                .build();
    }

    public static List<StoreInventoryResponseDto> toList(List<StoreInventory> storeInventories) {
        return storeInventories.stream()
                .map(storeInventory -> StoreInventoryResponseDto.builder()
                        .id(storeInventory.getId())
                        .storeId(storeInventory.getStoreId())
                        .sku(storeInventory.getSku())
                        .stock(storeInventory.getStock())
                        .build())
                .toList();
    }
}
