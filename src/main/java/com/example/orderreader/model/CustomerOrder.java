package com.example.orderreader.model;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@Getter
@ToString
public class CustomerOrder {
    private Integer userId;

    private String name;
    private Set<Order> orders;

    public static CustomerOrder with(Integer userId, String name) {
        final var customer = new CustomerOrder();
        customer.userId = userId;
        customer.name = name;
        return customer;
    }

    public void addOrder(final Order orderNew) {
        if (Objects.isNull(orders)) {
            orders = new HashSet<>();
        }
        if (orders.contains(orderNew)) {
           orders.stream()
                    .filter(item -> item.equals(orderNew))
                    .findFirst()
                    .ifPresent(currentOrder -> {
                        final var productSelected = orderNew.getProducts().stream().toList();
                        productSelected.forEach(currentOrder::addProduct);
                    });
        } else {
            orders.add(orderNew);
        }
    }
}
