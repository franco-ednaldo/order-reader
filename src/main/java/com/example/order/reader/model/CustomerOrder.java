package com.example.order.reader.model;

import com.example.order.reader.enums.TextFormat;
import lombok.*;

import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CustomerOrder {
    private Integer userId;
    private String name;
    private Set<Order> orders = new HashSet<>();

    private Map<Long, Order> ordersMap = new HashMap();

    public static CustomerOrder with(final String orderLine) {
        var fieldUserId = orderLine.substring(TextFormat.USER_ID.getStart(), TextFormat.USER_ID.getEnd()).trim();
        var fieldName = orderLine.substring(TextFormat.NAME.getStart(), TextFormat.NAME.getEnd()).trim();

        final var customer = new CustomerOrder();
        customer.userId = Integer.valueOf(fieldUserId);
        customer.name = fieldName;

        return customer;
    }

    public void addOrder(Order orderNew) {
        final var currentOrder = this.ordersMap.putIfAbsent(orderNew.getOrderId(), orderNew);
        if (Objects.nonNull(currentOrder)) {
            final var products = orderNew.getProducts();
            products.forEach(currentOrder::addProduct);
        }
    }

}
