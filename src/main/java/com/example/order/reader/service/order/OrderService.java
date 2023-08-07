package com.example.order.reader.service.order;

import com.example.order.reader.model.CustomerOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {
     List<CustomerOrder> process(final List<MultipartFile> files);

}
