package com.inventory.administration.infrastructure.repository.repository.storeinventory;

import com.inventory.administration.infrastructure.repository.entity.storeinventory.StoreInventoryData;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreInventoryJpaRepository extends JpaRepository<StoreInventoryData, Long> {
    Optional<StoreInventoryData> findByStoreIdAndSku(Long storeId, String sku);
    List<StoreInventoryData> findAllByStoreId(Long storeId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM StoreInventoryData s WHERE s.store.id = :storeId AND s.sku = :sku")
    Optional<StoreInventoryData> findByStoreIdAndSkuForUpdate(@Param("storeId") Long storeId, @Param("sku") String sku);
}