package com.example.order.reader.service;

import com.example.order.reader.mapper.CustomerMapper;
import com.example.order.reader.service.process.ProcessFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class OrderServiceTest {
    @Mock
    private CustomerMapper mapper;

    @Mock
    private List<ProcessFile> processFiles;

    @InjectMocks
    private  OrderService orderService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Método que recebe um ou mais arquivos .txt e faz o parser com sucesso")
    public void givenAValidFiles_whenCallProcess_thenShouldParserFiles() {

    }

}