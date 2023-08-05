package com.example.order.reader.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@ToString
public class CustomerOrder {
    private Integer userId;

    private String name;
    private Set<Order> orders = new HashSet<>();

    public static CustomerOrder with(Integer userId, String name) {
        final var customer = new CustomerOrder();
        customer.userId = userId;
        customer.name = name;
        return customer;
    }

    public void addOrder(final Order orderNew) {
        if (!orders.add(orderNew)) {
           orders.stream()
                    .filter(item -> item.equals(orderNew))
                    .findFirst()
                    .ifPresent(currentOrder -> {
                        final var productSelected = orderNew.getProducts().stream().toList();
                        productSelected.forEach(currentOrder::addProduct);
                    });
        }
    }
}
