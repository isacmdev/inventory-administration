package com.inventory.administration.infrastructure.repository.repository.storeinventory;

import com.inventory.administration.infrastructure.repository.entity.storeinventory.StoreInventoryData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreInventoryJpaRepository extends JpaRepository<StoreInventoryData, Long> {
    Optional<StoreInventoryData> findByStoreIdAndSku(Long storeId, String sku);
    List<StoreInventoryData> findAllByStoreId(Long storeId);
}
