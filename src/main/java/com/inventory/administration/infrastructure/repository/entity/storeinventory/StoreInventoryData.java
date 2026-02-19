package com.inventory.administration.infrastructure.repository.entity.storeinventory;

import com.inventory.administration.infrastructure.repository.entity.store.StoreData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_inventory")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreInventoryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreData store;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private int stock;
}
