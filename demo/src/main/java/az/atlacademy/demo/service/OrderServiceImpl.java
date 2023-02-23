package az.atlacademy.demo.service;

import az.atlacademy.demo.checker.CustomerChecker;
import az.atlacademy.demo.checker.ProductChecker;
import az.atlacademy.demo.client.RestConnection;
import az.atlacademy.demo.domain.CustomerResponse;
import az.atlacademy.demo.domain.ProductResponse;
import az.atlacademy.demo.dto.OrderDto;
import az.atlacademy.demo.mapper.OrderMapper;
import az.atlacademy.demo.repository.OrderRepository;
import az.atlacademy.demo.request.OrderRequest;
import az.atlacademy.demo.util.OrderNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    private final RestConnection operationService;
    private final ProductChecker productChecker;
    private final CustomerChecker customerChecker;

    private BigDecimal getPrice(OrderRequest orderRequest, ProductResponse productResponse) {
        return productResponse.getPrice().multiply(BigDecimal.valueOf(orderRequest.getCount()));
    }


    @Override
    public OrderDto getById(Long orderId) {
        return orderMapper.getEntityToDto(orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFound("User not found with id : " + orderId)));
    }

    @Override
    @Transactional
    public OrderDto makeOrder(OrderRequest orderRequest) {
        ProductResponse productResponse = getProductResponse(orderRequest);
        productChecker.check(productResponse, orderRequest);
        BigDecimal orderPrice = getPrice(orderRequest, productResponse);
        CustomerResponse customerExample = getCustomerResponse(orderRequest);
        customerChecker.checker(customerExample, orderPrice);
        operationService.decreaseBalance(customerExample.getId(), orderPrice.longValue());
        operationService.decreaseCount(orderRequest.getProductId(), orderRequest.getCount());
        var order = orderMapper.requestToEntity(orderRequest);
        order.setPrice(orderPrice.longValue());
        return orderMapper.getEntityToDto(orderRepository.save(order));
    }


    private CustomerResponse getCustomerResponse(OrderRequest orderRequest) {
        return operationService.getCustomer(orderRequest.getCustomerId()).getBody();
    }

    private ProductResponse getProductResponse(OrderRequest orderRequest) {
        return operationService.getProduct(orderRequest.getProductId()).getBody();
    }

    @Override
    public List<OrderDto> getAll() {
        return orderMapper.getEntityListToDtoList(orderRepository.findAll());
    }


}
