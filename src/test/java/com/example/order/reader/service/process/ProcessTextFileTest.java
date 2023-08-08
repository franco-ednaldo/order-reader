package com.example.order.reader.service.process;

import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.exception.ErrorParserFile;
import com.example.order.reader.model.Order;
import com.example.order.reader.service.mock.CustomerMock;
import com.example.order.reader.service.order.OrderServiceImpl;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.example.order.reader.service.mock.CustomerMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessTextFileTest {
    private ProcessTextFile processTextFile;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        processTextFile = spy(new ProcessTextFile());
    }

    @Test
    @DisplayName("Dado uma arquivo inválido deve ser retornar uma exception")
    void givenAValidFile_whenCallProcess_thenShouldReturnExcpetion() throws IOException {
        final var content = mockLineWithError();
        final var exceptedMessage = "File processing error";

        final var exception = Assertions.assertThrows(ErrorParserFile.class, ()-> {
            processTextFile.process(content);
        });

        assertAll("Exception details",
                () -> assertTrue(exception instanceof ErrorParserFile),
                () -> assertEquals(exceptedMessage, exception.getMessage())
        );
    }

    @Test
    @DisplayName("Dado uma arquivo com order deve ser retornar uma lista de customer")
    void givenAValidFile_whenCallProcess_thenShouldListCustomerOrder() throws IOException {
        final var expectedUserId = 70;
        final var expectedName = "Palmer Prosacco";
        final var expectedOrderId = 753l;
        final var expectedOrder = mockOrder();
        final var content = mockLine();
        final var processTextFile = spy(new ProcessTextFile());
        doReturn(null).when(processTextFile).accept(any());

        final var response = processTextFile.process(content);
        assertEquals(1, response.size());
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
    @DisplayName("Deve ser retornado true quando é passado um tipo válido")
    void givenAValidType_whenCallAccept_thenShouldReturnedTrue() {
        ProcessTextFile processTextFile = new ProcessTextFile();
        Boolean result = processTextFile.accept(TypeFile.FILE_TEXT);
        assertEquals(true, result);
    }

}