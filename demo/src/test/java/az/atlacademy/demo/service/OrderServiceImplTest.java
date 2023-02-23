//package az.atlacademy.demo.service;
//
//import az.atlacademy.demo.checker.CustomerChecker;
//import az.atlacademy.demo.checker.ProductChecker;
//import az.atlacademy.demo.client.Customer;
//import az.atlacademy.demo.client.RestConnection;
//import az.atlacademy.demo.client.Feign;
//import az.atlacademy.demo.domain.CustomerResponse;
//import az.atlacademy.demo.domain.Order;
//import az.atlacademy.demo.domain.ProductResponse;
//import az.atlacademy.demo.dto.OrderDto;
//import az.atlacademy.demo.repository.OrderRepository;
//import az.atlacademy.demo.request.OrderRequest;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.util.AssertionErrors.assertNotNull;
//
//@ContextConfiguration(classes = {OrderServiceImpl.class})
//@ExtendWith(MockitoExtension.class)
//class OrderServiceImplTest {
//
//    @MockBean
//    private CustomerChecker customerChecker;
//
//    @MockBean
//    private RestConnection forClient;
//
//    @Autowired
//    private OrderServiceImpl orderServiceImpl;
//
//    @MockBean
//    private ProductChecker productChecker;
//
//    @Mock
//    private OrderRepository orderRepository;
//    @Mock
//    private RestConnection client;
//    @Mock
//    private Feign product;
//    @Mock
//    private Customer customer;
//    @InjectMocks
//    private OrderServiceImpl orderService;
//
//    @Test
//    public void givenGetByIdWhenOrderFoundThenReturnOrderDto() {
//        //arrange
//        var order = new Order();
//        order.setId(1L);
//        order.setPrice(10L);
//        order.setOrderedAt(new Date());
//
//
//        Mockito.doReturn(Optional.of(order)).when(orderRepository).findById(ArgumentMatchers.anyLong());
//
//        //act
//        var orderDto = orderService.getById(1L);
//        //assert
//        assertNotNull("is not null", orderDto);
//    }
//
//    @Test
//    public void givenGetAllWhenAllFoundThenReturnList() {
//        var order = new Order();
//        order.setId(1L);
//        order.setPrice(10L);
//        order.setOrderedAt(new Date());
//        Mockito.doReturn(List.of(order)).when(orderRepository).findAll();
//
//        List<OrderDto> orderDtoList = orderService.getAll();
//        assertNotNull("is not null", orderDtoList);
//
//
//    }
//
//    @Test
//    public void givenGetByIdWhenOrderNotFoundThenThrowException() {
//        Mockito.doReturn(Optional.empty()).when(orderRepository).findById(ArgumentMatchers.anyLong());
//        Assertions.assertThatThrownBy(() -> orderService.getById(9L)).isInstanceOf(UserNotFound.class).hasMessage("Unavailable");
//    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testMakeOrder() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException: Cannot invoke "az.atlacademy.demo.domain.ProductResponse.getPrice()" because "productResponse" is null
//        //       at az.atlacademy.demo.service.OrderServiceImpl.makeOrder(OrderServiceImpl.java:45)
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        when(product.getProductById((Long) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
//        doNothing().when(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//        orderServiceImpl.makeOrder(new OrderRequest());
//    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
////    @Test
////    void testMakeOrder2() {
////        when(product.getProductById((Long) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
////        doThrow(new UserNotFound("An error occurred")).when(productChecker)
////                .check((ProductResponse) any(), (OrderRequest) any());
////        assertThrows(UserNotFound.class, () -> orderServiceImpl.makeOrder(new OrderRequest()));
////        verify(product).getProductById((Long) any());
////        verify(productChecker).check((ProductResponse) any(), (OrderRequest) any());
////    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testMakeOrder3() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because the return value of "az.atlacademy.demo.client.Feign.getProductById(java.lang.Long)" is null
//        //       at az.atlacademy.demo.service.OrderServiceImpl.makeOrder(OrderServiceImpl.java:43)
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        when(product.getProductById((Long) any())).thenReturn(null);
//        doNothing().when(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//        orderServiceImpl.makeOrder(new OrderRequest());
//    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testMakeOrder4() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the return value of "az.atlacademy.demo.request.OrderRequest.getCount()" is null
//        //       at az.atlacademy.demo.service.OrderServiceImpl.makeOrder(OrderServiceImpl.java:45)
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        when(product.getProductById((Long) any()))
//                .thenReturn(new ResponseEntity<>(new ProductResponse(), HttpStatus.CONTINUE));
//        doNothing().when(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//        orderServiceImpl.makeOrder(new OrderRequest());
//    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testMakeOrder5() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException: Cannot invoke "az.atlacademy.demo.request.OrderRequest.getProductId()" because "orderRequest" is null
//        //       at az.atlacademy.demo.service.OrderServiceImpl.makeOrder(OrderServiceImpl.java:43)
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        when(product.getProductById((Long) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
//        doNothing().when(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//        orderServiceImpl.makeOrder(null);
//    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testMakeOrder6() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.multiply(java.math.BigDecimal)" because the return value of "az.atlacademy.demo.domain.ProductResponse.getPrice()" is null
//        //       at az.atlacademy.demo.service.OrderServiceImpl.makeOrder(OrderServiceImpl.java:45)
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        when(product.getProductById((Long) any()))
//                .thenReturn(new ResponseEntity<>(new ProductResponse(), HttpStatus.CONTINUE));
//        doNothing().when(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setCount(3L);
//        orderServiceImpl.makeOrder(orderRequest);
//    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
//    @Test
//    void testMakeOrder7() {
//        Order order = new Order();
//        order.setCount(3);
//        order.setCustomerId(123L);
//        order.setId(123L);
//        order.setOrderedAt(new Date());
//        order.setPrice(1L);
//        order.setProductId(123L);
//        when(orderRepository.save((Order) any())).thenReturn(order);
//        doNothing().when(forClient).decreaseBalance((Long) any(), (Long) any());
//        doNothing().when(forClient).decreaseCount((Long) any(), (Long) any());
//        when(product.getProductById((Long) any())).thenReturn(
//                new ResponseEntity<>(new ProductResponse(123L, "Name", 3L, BigDecimal.valueOf(42L)), HttpStatus.CONTINUE));
//        when(customer.getCustomer((Long) any())).thenReturn(new CustomerResponse());
//        doNothing().when(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setCount(2L);
//        OrderDto actualMakeOrderResult = orderServiceImpl.makeOrder(orderRequest);
//        assertEquals(3, actualMakeOrderResult.getCount());
//        assertEquals(123L, actualMakeOrderResult.getProductId().longValue());
//        assertEquals(1L, actualMakeOrderResult.getPrice().longValue());
//        assertEquals(123L, actualMakeOrderResult.getId().longValue());
//        assertEquals(123L, actualMakeOrderResult.getCustomerId().longValue());
//        verify(orderRepository).save((Order) any());
//        verify(forClient).decreaseBalance((Long) any(), (Long) any());
//        verify(forClient).decreaseCount((Long) any(), (Long) any());
//        verify(product).getProductById((Long) any());
//        verify(customer).getCustomer((Long) any());
//        verify(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//    }
//
//    /**
//     * Method under test: {@link OrderServiceImpl#makeOrder(OrderRequest)}
//     */
//    @Test
//    void testMakeOrder8() {
//        Order order = new Order();
//        order.setCount(3);
//        order.setCustomerId(123L);
//        order.setId(123L);
//        order.setOrderedAt(new Date());
//        order.setPrice(1L);
//        order.setProductId(123L);
//        when(orderRepository.save((Order) any())).thenReturn(order);
//        doNothing().when(forClient).decreaseBalance((Long) any(), (Long) any());
//        doNothing().when(forClient).decreaseCount((Long) any(), (Long) any());
//        when(product.getProductById((Long) any())).thenReturn(
//                new ResponseEntity<>(new ProductResponse(123L, "Name", 3L, BigDecimal.valueOf(42L)), HttpStatus.CONTINUE));
//        when(customer.getCustomer((Long) any())).thenThrow(new UserNotFound("An error occurred"));
//        doNothing().when(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setCount(2L);
//        assertThrows(UserNotFound.class, () -> orderServiceImpl.makeOrder(orderRequest));
//        verify(product).getProductById((Long) any());
//        verify(customer).getCustomer((Long) any());
//        verify(productChecker).check((ProductResponse) any(), (OrderRequest) any());
//    }
//
////    @Test
////    public void givenGetProductByIdWhenIdFoundThenReturnProductResponse() {
////        ProductResponse productResponse = new ProductResponse();
////        productResponse.setId(1L);
////
////    }
//
//
//}