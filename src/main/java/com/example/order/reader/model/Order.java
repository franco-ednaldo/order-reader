package com.example.order.reader.model;


import com.example.order.reader.enums.TextFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.order.reader.util.LocalDateConverter.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private Long orderId;
    private Double total;

    private LocalDate date;

    private List<Product> products;

    public static Order with(final String line) {
        var fieldOrderId = line.substring(TextFormat.ORDER_ID.getStart(), TextFormat.ORDER_ID.getEnd()).trim();
        var fieldOrderDate = line.substring(TextFormat.DATE.getStart(), TextFormat.DATE.getEnd()).trim();

        final var order = new Order();
        order.setOrderId(Long.valueOf(fieldOrderId));
        order.setDate(formatStringToLocalDate(fieldOrderDate));
        order.total = 0.0;

        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public void addProduct(final Product item) {
        if (Objects.isNull(products)) {
            products = new ArrayList<>();
        }
        total += item.getValue();
        this.products.add(item);
    }
}
