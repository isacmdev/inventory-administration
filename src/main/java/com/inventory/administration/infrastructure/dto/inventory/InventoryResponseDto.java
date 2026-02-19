package com.inventory.administration.infrastructure.dto.inventory;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class InventoryResponseDto {
    private Long id;
    private String sku;
    private BigDecimal factoryPrice;
    private BigDecimal discount;
    private Integer totalStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
