package com.inventory.administration.infrastructure.security;

import com.inventory.administration.domain.ports.security.CurrentStoreProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtCurrentStoreProvider implements CurrentStoreProvider {

    @Override
    public Long getCurrentStoreId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return jwt.getClaim("storeId");
    }
}

