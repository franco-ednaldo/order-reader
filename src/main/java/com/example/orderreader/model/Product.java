package com.example.orderreader.model;

import lombok.*;

import java.security.cert.CertPathBuilder;

@NoArgsConstructor
@ToString
@Getter
public class Product {
    private Long productId;
    private Double value;

    public static Product with(Long productId, Double value) {
        final var product = new Product();
        product.productId = productId;
        product.value = value;
        return product;
    }
}
