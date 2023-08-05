package com.example.orderreader.model;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Order {

    private Long orderId;
    private Long total;
    private LocalDate date;
    private List<Product> products;
}
