package com.inventory.administration.domain.ports.store;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.infrastructure.dto.store.changepassword.PasswordChangeRequestDto;

import java.util.List;

public interface StoreInterfacePortIn {
    Store addStore(Store store);
    List<Store> getAllStores();
    Store getStoreById(Long id);
    Store updateStore(Long id, Store store);
    void deleteStore(Long id);
    String changePassword(Long id, PasswordChangeRequestDto dto);
}