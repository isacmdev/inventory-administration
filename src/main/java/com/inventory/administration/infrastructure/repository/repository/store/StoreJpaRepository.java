package com.inventory.administration.infrastructure.repository.repository.store;

import com.inventory.administration.infrastructure.repository.entity.store.StoreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreJpaRepository extends JpaRepository<StoreData, Long> {
    Optional<StoreData> findByEmail(String email);
}
