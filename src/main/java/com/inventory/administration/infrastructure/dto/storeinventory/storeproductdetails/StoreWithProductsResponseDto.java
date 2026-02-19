package com.inventory.administration.infrastructure.dto.storeinventory.storeproductdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreWithProductsResponseDto {
    private String name;
    private String address;
    private String city;

    private List<ProductDetailDto> products;
}