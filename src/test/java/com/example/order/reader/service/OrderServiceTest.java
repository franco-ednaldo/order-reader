package com.example.order.reader.service;

import com.example.order.reader.mapper.CustomerMapper;
import com.example.order.reader.service.order.OrderService;
import com.example.order.reader.service.order.OrderServiceImpl;
import com.example.order.reader.service.process.ProcessFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.example.order.reader.service.mock.CustomerMock.customerMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    @Mock
    private CustomerMapper mapper;

    @Mock
    private ProcessFile processFileMock;

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        List<ProcessFile> processFiles = Collections.singletonList(processFileMock);
        orderService = new OrderServiceImpl(mapper, processFiles);
    }

    @Test
    @DisplayName("Método que recebe um arquivo .txt e faz o parser com sucesso")
    void givenAValidFiles_whenCallProcess_thenShouldParserFiles() throws IOException {
        final var nameFile = "order.txt";
        MultipartFile file = new MockMultipartFile(nameFile, nameFile, "", "test-content".getBytes());
        when(processFileMock.accept(any())).thenReturn(true);
        when(processFileMock.process(any())).thenReturn(customerMock());

        final var response = orderService.process(List.of(file));

        assertEquals(1, response.size());
        verify(processFileMock, times(1)).accept(any());
        verify(processFileMock, times(1)).process(any());
        verifyNoMoreInteractions(processFileMock);
    }

    @Test
    @DisplayName("Método que recebe um arquivo .txt e retorna exeptiion caso o tipo de uma arquivo for inválido")
    void givenAnInValidFiles_whenCallProcess_thenShouldException() throws IOException {
        final var nameFile = "order.pdf";
        final var exceptedMessage = "Type of process not found.";

        MultipartFile file = new MockMultipartFile(nameFile, nameFile, "", "test-content".getBytes());
        when(processFileMock.accept(any())).thenReturn(false);

        final var exception = assertThrows(Exception.class, () -> {
            orderService.process(List.of(file));
        });


        assertTrue(exception.getMessage().contains(exceptedMessage));
        verify(processFileMock, times(1)).accept(any());
        verify(processFileMock, never()).process(any());
        verifyNoMoreInteractions(processFileMock);
    }

}