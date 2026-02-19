package com.inventory.administration.infrastructure.controller.inventory;

import com.inventory.administration.domain.entity.inventory.Inventory;
import com.inventory.administration.domain.ports.inventory.InventoryInterfacePortIn;
import com.inventory.administration.infrastructure.dto.inventory.InventoryRequestDto;
import com.inventory.administration.infrastructure.dto.inventory.InventoryResponseDto;
import com.inventory.administration.infrastructure.mapper.inventory.InventoryMapperDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor

public class InventoryController {

    private final InventoryInterfacePortIn inventoryPortIn;

    @PostMapping
    public ResponseEntity<InventoryResponseDto> create(@RequestBody InventoryRequestDto request) {

        Inventory inventory = InventoryMapperDto.toDomain(request);

        Inventory created = inventoryPortIn.createInventory(inventory);

        InventoryResponseDto response = InventoryMapperDto.toResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryResponseDto> update(@PathVariable Long id, @RequestBody InventoryRequestDto request) {
        Inventory inventory = InventoryMapperDto.toDomain(request);
        Inventory updated = inventoryPortIn.updateInventory(id, inventory);
        InventoryResponseDto response = InventoryMapperDto.toResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryPortIn.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
