package com.inventory.administration.infrastructure.dto.storeinventory.storeinventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInventoryResponseDto {
    private Long id;
    private Long storeId;
    private String sku;
    private int stock;
}
