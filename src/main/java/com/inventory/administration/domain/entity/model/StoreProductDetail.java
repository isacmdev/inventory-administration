package com.inventory.administration.domain.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductDetail {

    private String sku;
    private Integer stock;

    private String productName;
    private String description;
    private String category;

    private BigDecimal factoryPrice;
    private BigDecimal discount;

}
