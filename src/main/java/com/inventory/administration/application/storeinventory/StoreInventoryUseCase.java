package com.inventory.administration.application.storeinventory;

import com.inventory.administration.domain.entity.inventory.Inventory;
import com.inventory.administration.domain.entity.model.StoreWithProducts;
import com.inventory.administration.domain.entity.store.Store;

import com.inventory.administration.domain.entity.model.StoreProductDetail;
import com.inventory.administration.domain.entity.storeinventory.StoreInventory;
import com.inventory.administration.domain.ports.inventory.InventoryInterfacePortOut;
import com.inventory.administration.domain.ports.product.ProductInterfacePortOut;
import com.inventory.administration.domain.ports.security.CurrentStoreProvider;
import com.inventory.administration.domain.ports.store.StoreInterfacePortOut;
import com.inventory.administration.domain.ports.storeinventory.StoreInventoryInterfacePortIn;
import com.inventory.administration.domain.ports.storeinventory.StoreInventoryInterfacePortOut;
import com.inventory.administration.infrastructure.dto.product.ProductResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreInventoryUseCase implements StoreInventoryInterfacePortIn {

    private final StoreInventoryInterfacePortOut storeInventoryInterfacePortOut;
    private final InventoryInterfacePortOut inventoryInterfacePortOut;
    private final ProductInterfacePortOut productInterfacePortOut;
    private final CurrentStoreProvider currentStoreProvider;
    private final StoreInterfacePortOut storeInterfacePortOut;

    @Override
    @Transactional

    public StoreInventory assignStock(StoreInventory storeInventory) {

        Inventory globalInventory = inventoryInterfacePortOut.findBySkuForUpdate(storeInventory.getSku())
                .orElseThrow(() -> new RuntimeException("Global inventory not found"));

        if (storeInventory.getStock() <= 0) {
            throw new IllegalArgumentException("Stock must be > 0");
        }

        if (globalInventory.getTotalStock() < storeInventory.getStock()) {
            throw new RuntimeException("Not enough global stock");
        }

        StoreInventory storeInv = storeInventoryInterfacePortOut
                .findByStoreIdAndSku(storeInventory.getStoreId(), storeInventory.getSku())
                .map(existing -> {
                    existing.setStock(existing.getStock() + storeInventory.getStock());
                    return existing;
                })
                .orElse(storeInventory);

        StoreInventory saved = storeInventoryInterfacePortOut.save(storeInv);

        globalInventory.setTotalStock(globalInventory.getTotalStock() - storeInventory.getStock());
        globalInventory.setUpdatedAt(LocalDateTime.now());
        inventoryInterfacePortOut.save(globalInventory);

        return saved;
    }

    @Override
    public StoreWithProducts getStoreWithProducts() {
        Long storeId = currentStoreProvider.getCurrentStoreId();
        return getStoreWithProducts(storeId);
    }

    @Override
    public StoreWithProducts getStoreWithProducts (Long storeId) {

         Store store = storeInterfacePortOut.findById(storeId)
                 .orElseThrow(() -> new RuntimeException("Store not found with id " + storeId));

        List<StoreInventory> storeInventories =
                storeInventoryInterfacePortOut.findAllByStoreId(storeId);

        if (storeInventories.isEmpty()) {
            return new StoreWithProducts(store.getName(), store.getAddress(), store.getCity(), List.of());
        }
        List<String> skus = storeInventories.stream()
                .map(StoreInventory::getSku)
                .toList();

        Map<String, Inventory> inventoryMap =
                inventoryInterfacePortOut.findAllBySkuIn(skus)
                        .stream()
                        .collect(Collectors.toMap(Inventory::getSku, i -> i));

        Map<String, ProductResponseDto> productMap =
                productInterfacePortOut.findBySkus(skus);

        List<StoreProductDetail> products = storeInventories.stream()
                .map(data -> {
                    Inventory inventory = inventoryMap.get(data.getSku());
                    ProductResponseDto product = productMap.get(data.getSku());

                    if (inventory == null || product == null) {
                        throw new RuntimeException("Data inconsistency for SKU " + data.getSku());
                    }

                    return new StoreProductDetail(
                            data.getSku(),
                            data.getStock(),
                            product.getName(),
                            product.getDescription(),
                            product.getCategory(),
                            inventory.getFactoryPrice(),
                            inventory.getDiscount()
                    );
                })
                .toList();

        return new StoreWithProducts(
                store.getName(),
                store.getAddress(),
                store.getCity(),
                products
        );
    }
}