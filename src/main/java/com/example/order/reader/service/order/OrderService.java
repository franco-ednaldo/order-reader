package com.example.order.reader.service.order;

import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.model.CustomerOrder;
import java.util.List;

public interface OrderService {
     List<CustomerOrder> processFile(List<String> lines, TypeFile typeFile);
}
