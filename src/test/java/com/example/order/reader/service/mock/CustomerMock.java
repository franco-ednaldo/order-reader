package com.example.order.reader.service.mock;

import com.example.order.reader.model.CustomerOrder;
import com.example.order.reader.model.Order;
import com.example.order.reader.model.Product;

import java.time.LocalDate;
import java.util.List;

public class CustomerMock {

    public static List<CustomerOrder> customerMock() {
        return List.of(
                CustomerOrder.with(
                        "0000000070                              " +
                                "Palmer Prosacco00000007530000000003     " +
                                "1836.7420210308")
        );
    }

    public static List<String> mockLine() {
        return List.of("0000000070                              " +
                "Palmer Prosacco00000007530000000003     " +
                "1836.7420210308");
    }

    public static List<String> mockLineWithError() {
        return List.of("0000000070                              " +
                "Palmer Prosaccoadsfadfadfadfasdfasdfasdfasdfasdfasdfasfsfasdf00000000000000000000000007530000000003     " +
                "1836.7420210308");
    }

    public static Order mockOrder() {
        final var order = new Order();
        order.setOrderId(753l);
        order.setDate(LocalDate.of(2021, 3, 8));
        order.setTotal(1836.74);
        final var products = new Product();
        products.setProductId(3l);
        products.setValue(1836.74);
        order.setProducts(List.of(products));
        return  order;
    }



}
