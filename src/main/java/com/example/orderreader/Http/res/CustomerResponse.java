package com.example.orderreader.Http.res;

import lombok.Builder;

import java.util.List;

public record CustomerResponse(
        Integer userId,
        String name,
        List<OrderResponse> orders

) {
    @Builder
    public CustomerResponse {}
}
