package com.example.orderreader.model;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerOrder {

    private Integer userId;

    private String name;
    private Set<Order> orders;

    public static CustomerOrder with(Integer userId, String name) {
        final var customer = new CustomerOrder();
        customer.setUserId(userId);
        customer.setName(name);
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
                        productSelected.forEach(item -> currentOrder.addProduct(item));
                    });
        } else {
            orders.add(orderNew);
        }
    }
}
