package com.example.orderreader.service.process;

import com.example.orderreader.enums.TypeFile;
import com.example.orderreader.model.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProcessFile {


    List<Customer> process(MultipartFile file);

    Boolean accept(TypeFile type);
}
