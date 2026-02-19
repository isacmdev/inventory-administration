package com.inventory.administration.domain.ports.auth;

import com.inventory.administration.domain.entity.auth.StoreAuth;

public interface StoreAuthInterfacePortOut {
    StoreAuth save(StoreAuth auth);

}
