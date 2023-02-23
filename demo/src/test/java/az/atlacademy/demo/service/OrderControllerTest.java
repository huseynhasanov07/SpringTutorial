package az.atlacademy.demo.service;

import az.atlacademy.demo.DemoApplication;
import az.atlacademy.demo.checker.ProductChecker;
import az.atlacademy.demo.client.RestConnection;
import az.atlacademy.demo.config.WireMockConfig;
import az.atlacademy.demo.domain.CustomerResponse;
import az.atlacademy.demo.domain.Order;
import az.atlacademy.demo.domain.ProductResponse;
import az.atlacademy.demo.dto.OrderDto;
import az.atlacademy.demo.repository.OrderRepository;
import az.atlacademy.demo.request.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "integration")
@ContextConfiguration(classes = {WireMockConfig.class})
class OrderControllerTest {
    @Autowired
    WireMockServer wireMock;
    @Autowired
    RestConnection restConnection;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private OrderRepository orderRepository;


//    @Test
//    @Sql(scripts = {"classpath:insert-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    public void givenFindAllWhenFoundOrdersThenReturnOrderList() {
//        ResponseEntity<OrderDto[]> response = restTemplate.getForEntity(
//                "http://localhost:" + port + "/orders", OrderDto[].class);
//        assertEquals("", HttpStatus.OK, response.getStatusCode());
//        assertEquals("", 1, Objects.requireNonNull(response.getBody()).length);
//        assertEquals("", 1L, Arrays.stream(response.getBody()).toList().getProduct(0).getId());
//    }

    @Test
    @Sql(scripts = {"classpath:insert-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenFindAllWhenFoundOrdersThenReturnOrderList() {
        ResponseEntity<OrderDto[]> result = restTemplate.withBasicAuth("admin", "admin")
                .getForEntity("http://localhost:" + port + "/orders/private", OrderDto[].class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    @Sql(scripts = {"classpath:insert-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenFindOrderWhenFoundThenReturnOrder() {
        ResponseEntity<OrderDto> response = restTemplate.withBasicAuth("admin", "admin").getForEntity("http://localhost:" + port + "/orders/1", OrderDto.class);
        assertEquals("", HttpStatus.OK, response.getStatusCode());
        OrderDto orderDto = response.getBody();
        assertEquals("", HttpStatus.OK, response.getStatusCode());
        assert orderDto != null;
        assertEquals("", 1L, orderDto.getId());
    }

    @Test
    @Sql(scripts = {"classpath:insert-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenFindOrderWhenNotFoundThenReturnResult() {
        ResponseEntity<OrderDto> response = restTemplate.getForEntity("http://localhost:" + port + "/orders/private/2", OrderDto.class);
        assertEquals("", HttpStatus.CONFLICT, response.getStatusCode());
        assertNull("", response.getBody());
    }

    @Test
    @Sql(scripts = {"classpath:insert-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenFindOrderWhenFoundThenReturnOrder1() {
        ResponseEntity<OrderDto> response = restTemplate.getForEntity("http://localhost:" + port + "/orders/private/1", OrderDto.class);
        assertEquals("", HttpStatus.OK, response.getStatusCode());
        OrderDto orderDto = response.getBody();
        Order order = orderRepository.getReferenceById(1L);
        assertEquals("", HttpStatus.OK, response.getStatusCode());
        assert orderDto != null;
        assertEquals("", order.getId(), orderDto.getId());
    }

    @Test
    public void givenCreateOrderWhenCreatedThenReturnOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCount(5L);
        orderRequest.setProductId(1L);
        orderRequest.setCustomerId(1L);
        ProductResponse productResponse = ProductResponse.builder()
                .id(orderRequest.getProductId())
                .price(BigDecimal.valueOf(10))
                .count(5L)
                .build();
        ProductChecker productChecker = mock(ProductChecker.class);
        productChecker.check(productResponse, orderRequest);
        ResponseEntity<OrderDto> response = restTemplate.postForEntity("http://localhost:" + port + "/orders/public", orderRequest, OrderDto.class);
        Order createdOrder = orderRepository.getReferenceById(1L);
        assertEquals("", orderRequest.getCount(), createdOrder.getCount());


    }

    @Test
    public void testProduct() throws JsonProcessingException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCount(5L);
        orderRequest.setProductId(1L);
        orderRequest.setCustomerId(1L);

        ProductResponse productResponse = ProductResponse.builder()
                .id(orderRequest.getProductId())
                .price(BigDecimal.valueOf(10))
                .count(5L)
                .build();
        wireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/v1/products/1")).
                willReturn(WireMock.aResponse().
                        withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(new ObjectMapper().writeValueAsString(productResponse))));
    }

    @Test
    public void testCustomer() throws JsonProcessingException {
        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(1L)
                .name("IDK")
                .surname("IDK")
                .age(25)
                .balance(500)
                .build();
        wireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/v1/clients/1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(new ObjectMapper().writeValueAsString(customerResponse))));

    }


}
