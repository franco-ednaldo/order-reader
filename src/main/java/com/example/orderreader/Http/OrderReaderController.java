package com.example.orderreader.Http;

import com.example.orderreader.Http.res.CustomerResponse;
import com.example.orderreader.mapper.CustomerMapper;
import com.example.orderreader.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderReaderController {
    private final OrderService orderService;

    private final CustomerMapper mapper;

    public OrderReaderController(final OrderService orderService, CustomerMapper mapper){
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping
    public List<CustomerResponse> getOrders(@RequestParam("files") List<MultipartFile> files) {
        log.info("#### START PROCESS FILES ###");
        final var response = orderService.process(files);
        return mapper.customerToResponse(response);
    }

}
