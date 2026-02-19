package com.inventory.administration.domain.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreWithProducts {
    private String name;
    private String address;
    private String city;
    private List<StoreProductDetail> products;
}
