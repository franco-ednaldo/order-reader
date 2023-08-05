package com.example.order.reader.service.process;

import com.example.order.reader.enums.TextFormat;
import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.model.CustomerOrder;
import com.example.order.reader.model.Product;
import com.example.order.reader.model.Order;
import com.example.order.reader.util.LocalDateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ProcessTextFile implements ProcessFile {
    @Override
    public List<CustomerOrder> process(final MultipartFile file) {
        log.info("##### PROCESS FILE .TXT ######");
        try (var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = null;
            Map<Integer,CustomerOrder> mapCustomer = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                var fieldUserId = line.substring(TextFormat.USER_ID.getStart(), TextFormat.USER_ID.getEnd()).trim();
                var fieldName = line.substring(TextFormat.NAME.getStart(), TextFormat.NAME.getEnd()).trim();
                var fieldOrderId = line.substring(TextFormat.ORDER_ID.getStart(), TextFormat.ORDER_ID.getEnd()).trim();
                var fieldProductId = line.substring(TextFormat.PRODUCT_ID.getStart(), TextFormat.PRODUCT_ID.getEnd()).trim();
                var fieldProductValue = line.substring(TextFormat.VALUE_PRODUCT.getStart(), TextFormat.VALUE_PRODUCT.getEnd()).trim();
                var fieldOrderDate = line.substring(TextFormat.DATE.getStart(), TextFormat.DATE.getEnd()).trim();

                final var userId = Integer.valueOf(fieldUserId);

                final var customerNew = mapCustomer.containsKey(userId)?
                        mapCustomer.get(userId) : CustomerOrder.with(Integer.valueOf(fieldUserId), fieldName);

                final var orderNew = Order.with(Long.valueOf(fieldOrderId), LocalDateConverter.formatStringToLocalDate(fieldOrderDate));
                orderNew.addProduct(Product.with(Long.valueOf(fieldProductId), Double.valueOf(fieldProductValue)));

                customerNew.addOrder(orderNew);

                mapCustomer.put(customerNew.getUserId(), customerNew);
            }


            return mapCustomer.values().stream().toList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return List.of();
    }

    @Override
    public Boolean accept(final TypeFile type) {
        return TypeFile.FILE_TEXT.equals(type);
    }
}
