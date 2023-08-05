package com.example.order.reader.http.res;

import java.util.List;

public record CustomerResponse(
        Integer userId,
        String name,
        List<OrderResponse> orders

) {
}
