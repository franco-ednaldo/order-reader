package com.example.order.reader.service.process;

import com.example.order.reader.enums.TextFormat;
import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.exception.ErrorParserFile;
import com.example.order.reader.model.CustomerOrder;
import com.example.order.reader.model.Product;
import com.example.order.reader.model.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessTextFile implements ProcessFile {

    @Override
    public Boolean accept(final TypeFile type) {
        return TypeFile.FILE_TEXT.equals(type);
    }

    @Override
    public List<CustomerOrder> process(List<String> lines) {
        try {
            Map<Integer, CustomerOrder> mapCustomer = new HashMap<>();
            for (String line : lines) {
                var fieldUserId = line.substring(TextFormat.USER_ID.getStart(), TextFormat.USER_ID.getEnd()).trim();
                final var userId = Integer.valueOf(fieldUserId);

                final var customerNew = mapCustomer.containsKey(userId) ?
                        mapCustomer.get(userId) :  CustomerOrder.with(line);

                final var orderNew = Order.with(line);

                orderNew.addProduct(Product.with(line));
                customerNew.addOrder(orderNew);
                mapCustomer.put(customerNew.getUserId(), customerNew);
            }
            return mapCustomer.values().stream().toList();
        } catch (Exception ex) {
            throw new ErrorParserFile("File processing error", ex);
        }
    }
}
