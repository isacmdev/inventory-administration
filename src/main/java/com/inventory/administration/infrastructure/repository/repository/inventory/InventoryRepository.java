package com.inventory.administration.infrastructure.repository.repository.inventory;

import com.inventory.administration.domain.entity.inventory.Inventory;
import com.inventory.administration.domain.ports.inventory.InventoryInterfacePortOut;
import com.inventory.administration.infrastructure.repository.entity.inventory.InventoryData;
import com.inventory.administration.infrastructure.repository.mapper.inventory.InventoryEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class InventoryRepository implements InventoryInterfacePortOut {

    private final InventoryJpaRepository inventoryJpaRepository;

    @Override
    public Optional<Inventory> findBySku(String sku) {
        return inventoryJpaRepository.findBySku(sku)
                .map(InventoryEntityMapper::toDomain);
    }

    @Override
    public Inventory save(Inventory inventory) {
        InventoryData data =
                InventoryEntityMapper.toData(inventory);

        InventoryData saved =
                inventoryJpaRepository.save(data);

        return InventoryEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryJpaRepository.findById(id)
                .map(InventoryEntityMapper::toDomain);
    }

    @Override
    public Inventory update(Long id, Inventory inventory) {
        try{
            InventoryData data = inventoryJpaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Inventory not found by id " + id));

            Inventory updatedInventory = InventoryEntityMapper.toDomain(data);
            updatedInventory.setSku(inventory.getSku());
            updatedInventory.setFactoryPrice(inventory.getFactoryPrice());
            updatedInventory.setDiscount(inventory.getDiscount());
            updatedInventory.setTotalStock(inventory.getTotalStock());

            InventoryData updatedData = InventoryEntityMapper.toData(updatedInventory);

            InventoryData saved = inventoryJpaRepository.save(updatedData);

            return InventoryEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error updating inventory with id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) {
        inventoryJpaRepository.deleteById(id);
    }

    @Override
    public List<Inventory> findAllBySkuIn(List<String> skus) {
        return inventoryJpaRepository.findBySkuIn(skus).stream()
                .map(InventoryEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Inventory> findBySkuForUpdate(String sku) {
        return inventoryJpaRepository.findBySkuForUpdate(sku)
                .map(InventoryEntityMapper::toDomain);
    }
}
