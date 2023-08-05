package com.example.orderreader.model;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Customer {

    private Integer userId;
    private String name;
    private List<Order> orders;
}
