package com.example.order.reader.service;


import static com.example.order.reader.service.mock.CustomerMock.mockOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.example.order.reader.exception.ErrorParserFile;
import com.example.order.reader.exception.FileProcessNotFound;
import com.example.order.reader.model.Order;
import com.example.order.reader.service.order.OrderService;
import com.example.order.reader.service.order.OrderServiceImpl;
import com.example.order.reader.service.process.ProcessFile;
import com.example.order.reader.service.process.ProcessTextFile;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.*;

import static com.example.order.reader.enums.TypeFile.FILE_TEXT;
import static com.example.order.reader.service.mock.CustomerMock.mockLine;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    private ProcessFile processFileMock;

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        processFileMock = spy(new ProcessTextFile());
        List<ProcessFile> processFiles = Collections.singletonList(processFileMock);
        orderService = new OrderServiceImpl(processFiles);
    }

    @Test
    @DisplayName("Método que recebe um arquivo .txt e faz o parser com sucesso")
    void givenAValidFiles_whenCallProcess_thenShouldParserFiles() throws IOException {
        final var expectedUserId = 70;
        final var expectedName = "Palmer Prosacco";
        final var expectedOrderId = 753l;
        final var expectedOrder = mockOrder();

        when(processFileMock.accept(any())).thenReturn(true);

        final var response = orderService.processFile(mockLine(), FILE_TEXT);

        assertEquals(1, response.size());
        verify(processFileMock, times(1)).accept(any());
        verify(processFileMock, times(1)).process(any());
        verifyNoMoreInteractions(processFileMock);

        assertThat(response.get(0), allOf(
                hasProperty("userId", equalTo(expectedUserId)),
                hasProperty("name", equalTo(expectedName)),
                hasProperty("ordersMap", hasEntry(equalTo(expectedOrderId), hasOrderEqualTo(expectedOrder)))
        ));

    }

    private Matcher<Order> hasOrderEqualTo(Order expectedOrder) {
        return new TypeSafeMatcher<>() {
            @Override
            protected boolean matchesSafely(Order actualOrder) {
                return expectedOrder.getOrderId().equals( actualOrder.getOrderId())
                        && expectedOrder.getDate().equals(actualOrder.getDate())
                        && expectedOrder.getProducts().equals(actualOrder.getProducts());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("An Order with all attributes equal to " + expectedOrder);
            }
        };
    }

    @Test
    @DisplayName("Método que recebe um arquivo .txt e retorna exeptiion caso o tipo de uma arquivo for inválido")
    void givenAnInValidFiles_whenCallProcess_thenShouldException() throws IOException {
        final var exceptedMessage = "Type of process not found.";
        when(processFileMock.accept(any())).thenReturn(false);

        final var exception = assertThrows(FileProcessNotFound.class, () ->
            orderService.processFile(mockLine(), FILE_TEXT));

        assertAll("Exception details",
                () -> assertTrue(exception instanceof FileProcessNotFound),
                () -> assertEquals(exceptedMessage, exception.getMessage())
        );

        verify(processFileMock, times(1)).accept(any());
        verify(processFileMock, never()).process(any());
        verifyNoMoreInteractions(processFileMock);
    }

}