package az.atlacademy.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import az.atlacademy.demo.dto.OrderDto;
import az.atlacademy.demo.request.OrderRequest;
import az.atlacademy.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    /**
     * Method under test: {@link OrderController#createOrder(OrderRequest)}
     */
    @Test
    void testCreateOrder() throws Exception {
        when(orderService.makeOrder((OrderRequest) any())).thenReturn(new OrderDto());

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCount(3L);
        orderRequest.setCustomerId(123L);
        orderRequest.setProductId(123L);
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/public")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"customerId\":null,\"productId\":null,\"count\":0,\"price\":null}"));
    }

    /**
     * Method under test: {@link OrderController#decreaseProductCount(OrderRequest)}
     */
    @Test
    void testDecreaseProductCount() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCount(3L);
        orderRequest.setCustomerId(123L);
        orderRequest.setProductId(123L);
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link OrderController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(orderService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/private");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#findAll()}
     */
    @Test
    void testFindAll2() throws Exception {
        when(orderService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/orders/private");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#findOrder(Long)}
     */
    @Test
    void testFindOrder() throws Exception {
        when(orderService.getById((Long) any())).thenReturn(new OrderDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/private/{id}", 123L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"customerId\":null,\"productId\":null,\"count\":0,\"price\":null}"));
    }

    /**
     * Method under test: {@link OrderController#findOrder(Long)}
     */
    @Test
    void testFindOrder2() throws Exception {
        when(orderService.getAll()).thenReturn(new ArrayList<>());
        when(orderService.getById((Long) any())).thenReturn(new OrderDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/private/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

