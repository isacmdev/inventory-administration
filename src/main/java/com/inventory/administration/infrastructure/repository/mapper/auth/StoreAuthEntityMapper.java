package com.inventory.administration.infrastructure.repository.mapper.auth;

import com.inventory.administration.domain.entity.auth.StoreAuth;
import com.inventory.administration.infrastructure.repository.entity.store.StoreData;

public class StoreAuthEntityMapper {
    private StoreAuthEntityMapper () {}

    public static void mapAuthToData (StoreAuth auth, StoreData data) {
        data.setEmail(auth.getEmail());
        data.setPassword(auth.getPassword());
    }

    public static StoreAuth toDomain (StoreData storeData) {
        return StoreAuth.builder()
                .email(storeData.getEmail())
                .password(storeData.getPassword())
                .build();
    }
}
