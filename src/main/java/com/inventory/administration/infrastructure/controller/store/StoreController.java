package com.inventory.administration.infrastructure.controller.store;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.domain.ports.store.StoreInterfacePortIn;

import com.inventory.administration.infrastructure.dto.store.StoreRequestDto;
import com.inventory.administration.infrastructure.dto.store.StoreResponseDto;
import com.inventory.administration.infrastructure.dto.store.changepassword.PasswordChangeRequestDto;
import com.inventory.administration.infrastructure.mapper.store.StoreMapperDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreInterfacePortIn storeInterfacePortIn;

    public StoreController(StoreInterfacePortIn storeInterfacePortIn) {
        this.storeInterfacePortIn = storeInterfacePortIn;
    }

    @PostMapping
    public ResponseEntity<StoreResponseDto> addStore(@RequestBody StoreRequestDto storeRequestDto) {
        Store transformStoreToDomain = StoreMapperDto.toDomain(storeRequestDto);

        Store save = storeInterfacePortIn.addStore(transformStoreToDomain);

        StoreResponseDto savedStore = StoreMapperDto.toResponse(save);

        return ResponseEntity.ok(savedStore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDto> updateStore(
            @PathVariable Long id,
            @RequestBody StoreRequestDto storeRequestDto
    ) {
        Store transformStoreToDomain = StoreMapperDto.toDomain(storeRequestDto);
        Store updatedStore = storeInterfacePortIn.updateStore(id, transformStoreToDomain);

        StoreResponseDto responseDto = StoreMapperDto.toResponse(updatedStore);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody PasswordChangeRequestDto dto) {
        storeInterfacePortIn.changePassword(id, dto);

        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }

    @GetMapping
    public ResponseEntity<java.util.List<StoreResponseDto>> getAllStores() {
        List<Store> stores = storeInterfacePortIn.getAllStores();
        List<StoreResponseDto> response = stores.stream()
                .map(StoreMapperDto::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDto> getStoreById(@PathVariable Long id) {
        Store store = storeInterfacePortIn.getStoreById(id);
        StoreResponseDto responseDto = StoreMapperDto.toResponse(store);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeInterfacePortIn.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}