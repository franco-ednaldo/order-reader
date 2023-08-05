package com.example.orderreader.Http.res;


import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Long total,
        LocalDate date,
        List<ProductResponse> products
) {
}
