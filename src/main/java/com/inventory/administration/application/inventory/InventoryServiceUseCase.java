package com.inventory.administration.application.inventory;

import com.inventory.administration.domain.entity.inventory.Inventory;
import com.inventory.administration.domain.ports.inventory.InventoryInterfacePortIn;
import com.inventory.administration.domain.ports.inventory.InventoryInterfacePortOut;
import com.inventory.administration.domain.ports.product.ProductInterfacePortOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServiceUseCase implements InventoryInterfacePortIn {

    private final InventoryInterfacePortOut inventoryPortOut;
    private final ProductInterfacePortOut productPortOut;

    @Override
    public Inventory createInventory(Inventory inventory) {

        if (inventoryPortOut.findBySku(inventory.getSku()).isPresent()) {
            throw new RuntimeException(
                    "Inventory already exists for sku " + inventory.getSku()
            );
        }

        LocalDateTime now = LocalDateTime.now();
        inventory.setCreatedAt(now);
        inventory.setUpdatedAt(now);

        return inventoryPortOut.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existing = inventoryPortOut.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found for id " + id));

        existing.setTotalStock(inventory.getTotalStock());
        existing.setDiscount(inventory.getDiscount());
        existing.setFactoryPrice(inventory.getFactoryPrice());
        existing.setUpdatedAt(LocalDateTime.now());

        return inventoryPortOut.update(id, existing);
    }

    @Override
    public void deleteInventory(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id del inventario no puede ser negativo");
        }

        Inventory inventory = inventoryPortOut.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found for id " + id));

        inventoryPortOut.deleteById(inventory.getId());
    }
}
