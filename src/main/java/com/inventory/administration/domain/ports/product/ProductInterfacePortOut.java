package com.inventory.administration.domain.ports.product;

import com.inventory.administration.infrastructure.dto.product.ProductResponseDto;
import com.inventory.administration.infrastructure.repository.entity.inventory.InventoryData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductInterfacePortOut {
    Optional<ProductResponseDto> findBySku(String sku);
    Map<String, ProductResponseDto> findBySkus(List<String> skus);
}
