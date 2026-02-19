package com.inventory.administration.infrastructure.repository.mapper.store;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.infrastructure.repository.entity.store.StoreData;

public class StoreEntityMapper {
    private StoreEntityMapper () {}

    public static Store toDomain (StoreData storeData) {
        return Store.builder()
                .id(storeData.getId())
                .name(storeData.getName())
                .address(storeData.getAddress())
                .city(storeData.getCity())
                .email(storeData.getEmail())
                .password(storeData.getPassword())
                .build();
    }

    public static StoreData toData (Store store) {
        return StoreData.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .city(store.getCity())
                .email(store.getEmail())
                .password(store.getPassword())
                .build();
    }
}