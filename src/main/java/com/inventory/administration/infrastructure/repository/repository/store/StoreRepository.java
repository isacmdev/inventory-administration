package com.inventory.administration.infrastructure.repository.repository.store;

import com.inventory.administration.domain.entity.store.Store;
import com.inventory.administration.domain.entity.auth.StoreAuth;
import com.inventory.administration.domain.ports.auth.StoreAuthInterfacePortOut;
import com.inventory.administration.domain.ports.store.StoreInterfacePortOut;
import com.inventory.administration.infrastructure.repository.entity.store.StoreData;
import com.inventory.administration.infrastructure.repository.mapper.auth.StoreAuthEntityMapper;
import com.inventory.administration.infrastructure.repository.mapper.store.StoreEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StoreRepository implements StoreInterfacePortOut, StoreAuthInterfacePortOut {
    private final StoreJpaRepository storeJpaRepository;

    @Override
    public Store save(Store store) {
        try{
            StoreData storeData = StoreEntityMapper.toData(store);
            StoreData saved = storeJpaRepository.save(storeData);
            return StoreEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error saving store", e);
        }
    }

    @Override
    public StoreAuth save(StoreAuth auth) {
        StoreData data = storeJpaRepository.findByEmail(auth.getEmail())
                .orElseThrow(() -> new RuntimeException("Store not found by email " + auth.getEmail()));

        StoreAuthEntityMapper.mapAuthToData(auth, data);

        StoreData saved = storeJpaRepository.save(data);

        return StoreAuthEntityMapper.toDomain(saved);
    }

    @Override
    public Store update(Long id, Store store) {
        try {
            StoreData storeData = storeJpaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Store not found by id " + id));

            storeData.setName(store.getName());
            storeData.setAddress(store.getAddress());
            storeData.setCity(store.getCity());
            storeData.setEmail(store.getEmail());
            storeData.setPassword(store.getPassword());

            StoreData saved = storeJpaRepository.save(storeData);

            return StoreEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error updating store with id " + id, e);
        }
    }

    @Override
    public List<Store> findAll() {
        try {
            return storeJpaRepository.findAll().stream()
                    .map(StoreEntityMapper::toDomain)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all stores", e);
        }
    }

    @Override
    public Optional<Store> findById(Long id) {
        try {
            return storeJpaRepository.findById(id)
                    .map(StoreEntityMapper::toDomain);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving store with id " + id, e);
        }
    }


    @Override
    public void deleteById(Long id) {
        try {
            storeJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting store with id " + id, e);
        }
    }
}
