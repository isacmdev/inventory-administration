package com.inventory.administration.infrastructure.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestDto {
    private String name;
    private String address;
    private String city;
    private String email;
    private String password;
}