package com.example.order.reader.http;

import com.example.order.reader.mapper.CustomerMapper;
import com.example.order.reader.mapper.CustomerMapperImpl;
import com.example.order.reader.service.OrderService;
import com.example.order.reader.service.process.ProcessTextFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderReaderController.class)
@Import({OrderService.class, ProcessTextFile.class})
class OrderReaderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CustomerMapperImpl mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Dado um arquivo valido deve ser retonardo status 200 e a lista de customer com seus orders")
    void givenAValidFileWhenCallApi_thenShouldReturnStatus200AndListCustomer() throws Exception {
        ClassPathResource resource = new ClassPathResource("order.txt");
        MockMultipartFile file = new MockMultipartFile(
                "files", "order.txt", MediaType.TEXT_PLAIN_VALUE, resource.getInputStream()
        );
        mockMvc.perform(
                        multipart("/orders")
                                .file(file)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)));
    }

    @Test
    @DisplayName("Dado um arquivo invalido deve ser retonardo status 400")
    void givenAValidFileWhenCallApi_thenShouldReturnStatus400() throws Exception {
        ClassPathResource resource = new ClassPathResource("order2.txt");
        MockMultipartFile file = new MockMultipartFile(
                "files", "order.txt", MediaType.TEXT_PLAIN_VALUE, resource.getInputStream()
        );
        mockMvc.perform(
                        multipart("/orders")
                                .file(file)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}