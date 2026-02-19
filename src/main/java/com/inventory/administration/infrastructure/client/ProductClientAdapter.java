package com.inventory.administration.infrastructure.client;

import com.inventory.administration.domain.ports.product.ProductInterfacePortOut;
import com.inventory.administration.infrastructure.dto.product.ProductResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductClientAdapter implements ProductInterfacePortOut {
    private final WebClient webClient;

    @Override
    public Optional<ProductResponseDto> findBySku(String sku) {
        try {
            ProductResponseDto response = webClient.get()
                    .uri("http://products-app:8086/products/sku/{sku}", sku)
                    .retrieve()
                    .bodyToMono(ProductResponseDto.class)
                    .block();

            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Map<String, ProductResponseDto> findBySkus(List<String> skus) {
        return webClient.post()
                .uri("http://products-app:8086/products/batch")
                .bodyValue(skus)
                .retrieve()
                .bodyToFlux(ProductResponseDto.class)
                .collectMap(ProductResponseDto::getSku)
                .block();
    }
}
