package com.example.order.reader.model;

import com.example.order.reader.enums.TextFormat;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Product {
    private Long productId;
    private Double value;

    public static Product with(String line) {
        var fieldProductId = line.substring(TextFormat.PRODUCT_ID.getStart(), TextFormat.PRODUCT_ID.getEnd()).trim();
        var fieldProductValue = line.substring(TextFormat.VALUE_PRODUCT.getStart(), TextFormat.VALUE_PRODUCT.getEnd()).trim();

        final var product = new Product();
        product.productId = Long.valueOf(fieldProductId);
        product.value = Double.valueOf(fieldProductValue);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
