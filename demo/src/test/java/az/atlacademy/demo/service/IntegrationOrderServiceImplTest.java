//package az.atlacademy.demo.service;
//
//import az.atlacademy.demo.checker.CustomerChecker;
//import az.atlacademy.demo.checker.ProductChecker;
//import az.atlacademy.demo.client.Customer;
//import az.atlacademy.demo.client.RestConnection;
//import az.atlacademy.demo.client.Feign;
//import az.atlacademy.demo.repository.OrderRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//
//@SpringBootTest
//@ActiveProfiles(profiles = "integration")
//@EnableConfigurationProperties
//@EnableJpaRepositories("az.atlacademy.demo.repository")
//public class IntegrationOrderServiceImplTest {
//    OrderServiceImpl orderService;
//    @Autowired
//    OrderRepository orderRepository;
//    @Mock
//    RestConnection client;
//    @Mock
//    Feign feign;
//    @Mock
//    Customer customer;
//    @Mock
//    ProductChecker productChecker;
//    @Mock
//    CustomerChecker customerChecker;
//
//    @BeforeEach
//    public void init() {
//        orderService = new OrderServiceImpl(orderRepository, client, feign, customer, productChecker, customerChecker);
//    }
//
//    @Sql(scripts = {"classpath:insert-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Test
//    public void givenGetAllWhenFoundThenReturnList() {
//        var orders = orderService.getAll();
//        Assertions.assertFalse(orders.isEmpty());
//        Assertions.assertEquals(1, orders.get(0).getId());
//    }
//
//    @Test
//    @Sql(scripts = {"classpath:insert-orders.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    public void givenGetByIdWhenFoundThenReturnResult() {
//        Long id = 1L;
//        var order = orderService.getById(id);
//        Assertions.assertEquals(id,order.getId());
//    }
//}
