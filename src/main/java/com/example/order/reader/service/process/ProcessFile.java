package com.example.order.reader.service.process;

import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.model.CustomerOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProcessFile {


    List<CustomerOrder> process(MultipartFile file);

    Boolean accept(TypeFile type);
}
