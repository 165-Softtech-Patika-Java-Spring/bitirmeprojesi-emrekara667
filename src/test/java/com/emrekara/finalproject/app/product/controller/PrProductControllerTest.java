package com.emrekara.finalproject.app.product.controller;

import com.emrekara.finalproject.app.gen.BaseTest;
import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.product.dto.PrProductSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PrProductControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/products";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void save() throws Exception {


        PrProductSaveRequestDto prProductSaveRequestDto = PrProductSaveRequestDto.builder()
                .productType(ProductType.FOOD)
                .productName("Test")
                .vatFreePrice(BigDecimal.ONE)
                .build();

        String content = objectMapper.writeValueAsString(prProductSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void updatePrice() {
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void findProductByPriceBetween() {
    }

    @Test
    void productDetails() {
    }
}