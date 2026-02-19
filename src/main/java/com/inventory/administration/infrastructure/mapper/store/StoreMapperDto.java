package com.inventory.administration.infrastructure.mapper.store;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.infrastructure.dto.store.StoreRequestDto;
import com.inventory.administration.infrastructure.dto.store.StoreResponseDto;

import java.util.List;

public class StoreMapperDto {
    private StoreMapperDto () {}

    public static Store toDomain (StoreRequestDto storeRequestDto) {
        return Store.builder()
                .name(storeRequestDto.getName())
                .address(storeRequestDto.getAddress())
                .city(storeRequestDto.getCity())
                .email(storeRequestDto.getEmail())
                .password(storeRequestDto.getPassword())
                .build();
    }

    public static StoreResponseDto toResponse (Store store) {
        return StoreResponseDto.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .city(store.getCity())
                .build();
    }

    public static List<StoreResponseDto> toStoreList (List<Store> stores) {
        return stores.stream()
                .map(store -> StoreResponseDto.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .address(store.getAddress())
                        .city(store.getCity())
                        .build())
                .toList();
    }
}
