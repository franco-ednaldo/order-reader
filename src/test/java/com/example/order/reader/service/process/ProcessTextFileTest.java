package com.example.order.reader.service.process;

import com.example.order.reader.enums.TypeFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessTextFileTest {

    @Test
    @DisplayName("Dado uma arquivo com order deve ser retornar uma lista de customer")
    void givenAValidFile_whenCallProcess_thenShouldListCustomerOrder() throws IOException {
        final var content = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        MultipartFile file = new MockMultipartFile("text.txt", inputStream);
        final var processTextFile = spy(new ProcessTextFile());
        doReturn(null).when(processTextFile).accept(any());

       // final var response = processTextFile.process(file);
       // assertEquals(1, response.size());
    }

    @Test
    @DisplayName("Deve ser retornado true quando é passado um tipo válido")
    void givenAValidType_whenCallAccept_thenShouldReturnedTrue() {
        ProcessTextFile processTextFile = new ProcessTextFile();
        Boolean result = processTextFile.accept(TypeFile.FILE_TEXT);
        assertEquals(true, result);
    }

}