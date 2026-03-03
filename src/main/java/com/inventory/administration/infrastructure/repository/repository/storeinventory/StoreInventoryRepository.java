package com.inventory.administration.infrastructure.repository.repository.storeinventory;

import com.inventory.administration.domain.entity.storeinventory.StoreInventory;
import com.inventory.administration.domain.ports.storeinventory.StoreInventoryInterfacePortOut;
import com.inventory.administration.infrastructure.repository.entity.storeinventory.StoreInventoryData;
import com.inventory.administration.infrastructure.repository.mapper.storeinventory.StoreInventoryEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StoreInventoryRepository implements StoreInventoryInterfacePortOut {
    private final StoreInventoryJpaRepository storeInventoryJpaRepository;

    @Override
    public StoreInventory save(StoreInventory storeInventory) {
        try{
            StoreInventoryData storeData = StoreInventoryEntityMapper.toData(storeInventory);
            StoreInventoryData saved = storeInventoryJpaRepository.save(storeData);
            return StoreInventoryEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error saving store inventory", e);
        }
    }

    @Override
    public List<StoreInventory> findAllByStoreId(Long storeId) {
        try {
            return storeInventoryJpaRepository.findAllByStoreId(storeId)
                    .stream()
                    .map(StoreInventoryEntityMapper::toDomain)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all store inventory for store id " + storeId, e);
        }
    }

    @Override
    public Optional<StoreInventory> findByStoreIdAndSku(Long storeId, String sku) {
        return storeInventoryJpaRepository.findByStoreIdAndSku(storeId, sku)
                .map(StoreInventoryEntityMapper::toDomain);
    }

    @Override
    public void decrementStock(Long storeId, String sku, int quantity) {
        StoreInventoryData data = storeInventoryJpaRepository
                .findByStoreIdAndSkuForUpdate(storeId, sku)
                .orElseThrow(() -> new RuntimeException("Store inventory not found for store " + storeId + " and SKU " + sku));

        if (data.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock in store for SKU " + sku);
        }

        data.setStock(data.getStock() - quantity);
        StoreInventoryData saved = storeInventoryJpaRepository.save(data);
        StoreInventoryEntityMapper.toDomain(saved);
    }
}
