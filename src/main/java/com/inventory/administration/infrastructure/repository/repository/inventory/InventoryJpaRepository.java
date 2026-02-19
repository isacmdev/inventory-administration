package com.inventory.administration.infrastructure.repository.repository.inventory;

import com.inventory.administration.infrastructure.repository.entity.inventory.InventoryData;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryJpaRepository extends JpaRepository<InventoryData, Long> {
    Optional<InventoryData> findBySku(String sku);

    List<InventoryData> findBySkuIn(List<String> skus);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from InventoryData i where i.sku = :sku")
    Optional<InventoryData> findBySkuForUpdate(@Param("sku") String sku);
}
