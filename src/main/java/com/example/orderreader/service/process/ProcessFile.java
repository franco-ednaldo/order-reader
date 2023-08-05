package com.example.orderreader.service.process;

import com.example.orderreader.enums.TypeFile;
import com.example.orderreader.model.CustomerOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProcessFile {


    List<CustomerOrder> process(MultipartFile file);

    Boolean accept(TypeFile type);
}
