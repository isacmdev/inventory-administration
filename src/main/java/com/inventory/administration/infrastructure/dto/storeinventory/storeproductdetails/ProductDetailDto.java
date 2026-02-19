package com.inventory.administration.infrastructure.dto.storeinventory.storeproductdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {
    private String sku;
    private Integer storeStock;

    private String productName;
    private String description;
    private String category;

    private BigDecimal priceFactory;
    private BigDecimal discount;
}