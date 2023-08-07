package com.example.order.reader.service.order;

import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.exception.FileProcessNotFound;
import com.example.order.reader.model.CustomerOrder;
import com.example.order.reader.service.process.ProcessFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final List<ProcessFile> processFiles;

    public OrderServiceImpl( final  List<ProcessFile> processFiles){
        this.processFiles = processFiles;
    }

    @Override
    public List<CustomerOrder> processFile(List<String> lines, TypeFile typeFile) {
        final var processFile = processFiles.stream()
                .filter(type -> type.accept(typeFile))
                .findFirst()
                .orElseThrow(() -> new FileProcessNotFound("Type of process not found."));

        return processFile.process(lines);
    }

}
