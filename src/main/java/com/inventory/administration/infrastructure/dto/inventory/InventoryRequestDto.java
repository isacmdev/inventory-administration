package com.inventory.administration.infrastructure.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDto {
    private String sku;
    private BigDecimal factoryPrice;
    private BigDecimal discount;
    private Integer totalStock;
}
