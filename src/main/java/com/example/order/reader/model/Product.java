package com.example.order.reader.model;

import com.example.order.reader.enums.TextFormat;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
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
}
