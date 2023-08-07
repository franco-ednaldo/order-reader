package com.example.order.reader.service.process;

import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.model.CustomerOrder;

import java.util.List;

public interface ProcessFile {
    Boolean accept(TypeFile type);

    List<CustomerOrder> process(List<String> lines);
}
