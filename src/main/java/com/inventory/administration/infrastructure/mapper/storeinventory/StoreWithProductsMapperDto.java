package com.inventory.administration.infrastructure.mapper.storeinventory;

import com.inventory.administration.domain.entity.model.StoreProductDetail;
import com.inventory.administration.domain.entity.model.StoreWithProducts;
import com.inventory.administration.infrastructure.dto.storeinventory.storeproductdetails.ProductDetailDto;
import com.inventory.administration.infrastructure.dto.storeinventory.storeproductdetails.StoreWithProductsResponseDto;

import java.util.List;

public class StoreWithProductsMapperDto {
    private StoreWithProductsMapperDto () {}

    public static StoreWithProductsResponseDto toResponse(StoreWithProducts storeWithProducts) {

        List<ProductDetailDto> productDetails =
                storeWithProducts.getProducts()
                .stream()
                .map(StoreWithProductsMapperDto::toProductDto)
                .toList();

        return StoreWithProductsResponseDto.builder()
                .name(storeWithProducts.getName())
                .address(storeWithProducts.getAddress())
                .city(storeWithProducts.getCity())
                .products(productDetails)
                .build();


    }

    public static ProductDetailDto toProductDto (StoreProductDetail storeProductDetail) {
        return ProductDetailDto.builder()
                .sku(storeProductDetail.getSku())
                .storeStock(storeProductDetail.getStock())
                .productName(storeProductDetail.getProductName())
                .description(storeProductDetail.getDescription())
                .category(storeProductDetail.getCategory())
                .discount(storeProductDetail.getDiscount())
                .priceFactory(storeProductDetail.getFactoryPrice())
                .build();
    }
}
