package com.inventory.administration.infrastructure.controller.storeinventory;

import com.inventory.administration.domain.entity.model.StoreWithProducts;
import com.inventory.administration.domain.entity.storeinventory.StoreInventory;
import com.inventory.administration.domain.ports.storeinventory.StoreInventoryInterfacePortIn;

import com.inventory.administration.infrastructure.dto.storeinventory.storeinventory.StoreInventoryRequestDto;
import com.inventory.administration.infrastructure.dto.storeinventory.storeinventory.StoreInventoryResponseDto;
import com.inventory.administration.infrastructure.dto.storeinventory.storeproductdetails.StoreWithProductsResponseDto;
import com.inventory.administration.infrastructure.mapper.storeinventory.StoreInventoryMapperDto;
import com.inventory.administration.infrastructure.mapper.storeinventory.StoreWithProductsMapperDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store-inventory")
public class StoreInventoryController {
    private final StoreInventoryInterfacePortIn storeInventoryInterfacePortIn;

    public StoreInventoryController(StoreInventoryInterfacePortIn storeInventoryInterfacePortIn) {
        this.storeInventoryInterfacePortIn = storeInventoryInterfacePortIn;
    }

    @PostMapping
    public ResponseEntity<StoreInventoryResponseDto> assignProductToStore(@RequestBody StoreInventoryRequestDto requestDto) {
        StoreInventory domain = StoreInventoryMapperDto.toDomain(requestDto);

        StoreInventory saved = storeInventoryInterfacePortIn.assignStock(domain);

        return ResponseEntity.ok(StoreInventoryMapperDto.toResponse(saved));
    }

    @GetMapping("/details")
    public ResponseEntity<StoreWithProductsResponseDto> getStoreDetails(){
         StoreWithProducts domain = storeInventoryInterfacePortIn.getStoreWithProducts();
         return  ResponseEntity.ok(StoreWithProductsMapperDto.toResponse(domain));
    }

    @PatchMapping("/decrementStock")
    public ResponseEntity<StoreWithProductsResponseDto> updateStock(
            @RequestParam Long storeId,
            @RequestParam String sku,
            @RequestParam Integer quantityRemove
    ) {
        StoreWithProducts  updatedStore = storeInventoryInterfacePortIn.decrementStock(storeId, sku, quantityRemove);

        return  ResponseEntity.ok(StoreWithProductsMapperDto.toResponse(updatedStore));
    }
}
