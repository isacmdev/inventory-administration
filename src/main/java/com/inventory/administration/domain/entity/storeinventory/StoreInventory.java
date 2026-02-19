package com.inventory.administration.domain.entity.storeinventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInventory {
    private Long id;
    private Long storeId;
    private String sku;
    private Integer stock;
}
