package com.example.order.reader.service.mock;

import com.example.order.reader.model.CustomerOrder;
import com.example.order.reader.model.Order;
import com.example.order.reader.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CustomerMock {

    public static List<CustomerOrder> customerMock() {
        return List.of(
//                new CustomerOrder(1,
//                        "User test",
//                        Set.of(new Order(
//                                        1l,
//                                        2000.0,
//                                        LocalDate.now(),
//                                        List.of(
//                                            new Product(1l, 2000.0)
//                                        )
//                                )
//                        )
//                )
        );
    }

}
