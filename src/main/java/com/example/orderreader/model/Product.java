package com.example.orderreader.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Product {

    private Long productId;
    private Double value;
}
