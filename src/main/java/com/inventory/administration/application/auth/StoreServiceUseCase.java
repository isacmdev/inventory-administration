package com.inventory.administration.application.auth;

import com.inventory.administration.domain.ports.auth.AuthContextPort;

public class StoreServiceUseCase {

    private final AuthContextPort authContext;

    public StoreServiceUseCase(AuthContextPort authContext) {
        this.authContext = authContext;
    }

    public void doSomething() {
        String email = authContext.getCurrentUserEmail();
        System.out.println("User: " + email);
    }
}