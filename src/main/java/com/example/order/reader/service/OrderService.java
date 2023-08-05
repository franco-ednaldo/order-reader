package com.example.order.reader.service;

import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.mapper.CustomerMapper;
import com.example.order.reader.model.CustomerOrder;
import com.example.order.reader.service.process.ProcessFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class OrderService {
    private final CustomerMapper mapper;

    private final List<ProcessFile> processFiles;

    public OrderService(final CustomerMapper mapper, final  List<ProcessFile> processFiles){
        this.mapper = mapper;
        this.processFiles = processFiles;
    }

    public List<CustomerOrder> process(final List<MultipartFile> files) {
        return files.stream()
                .map(this::parser)
                .flatMap(List::stream)
                .toList();

    }

    private List<CustomerOrder> parser(final MultipartFile file) {
        final var processFile = processFiles.stream()
                .filter(type -> type.accept(TypeFile.getFileByExtension(file)))
                .findFirst()
                .orElseThrow();

        return processFile.process(file);
    }

}
