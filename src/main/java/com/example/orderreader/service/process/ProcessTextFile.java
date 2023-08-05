package com.example.orderreader.service.process;

import com.example.orderreader.enums.TextFormat;
import com.example.orderreader.enums.TypeFile;
import com.example.orderreader.model.Customer;
import com.example.orderreader.model.Order;
import com.example.orderreader.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProcessTextFile implements ProcessFile {
    @Override
    public List<Customer> process(final MultipartFile file) {
        log.info("##### PROCESS FILE .TXT ######");
        try (var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = null;
            List<Customer> result = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                final var customer = Customer.builder();
                var fieldUserId = line.substring(TextFormat.USER_ID.getStart(), TextFormat.USER_ID.getEnd());
                var fieldName = line.substring(TextFormat.NAME.getStart(), TextFormat.NAME.getEnd()).trim();
                var fieldOrderId = line.substring(TextFormat.ORDER_ID.getStart(), TextFormat.ORDER_ID.getEnd());
                var fieldProductId = line.substring(TextFormat.PRODUCT_ID.getStart(), TextFormat.PRODUCT_ID.getEnd());

                customer
                        .userId(Integer.valueOf(fieldUserId))
                        .name(fieldName)
                        .orders(List.of(Order.builder()
                                        .orderId(Long.valueOf(fieldOrderId))
                                        .products(List.of(Product.builder()
                                                        .productId(Long.valueOf(fieldProductId))
                                                .build()))
                                .build()));

                result.add(customer.build());
            }

            return result;
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
