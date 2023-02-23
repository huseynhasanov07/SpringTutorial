package az.atlacademy.demo.service;

import az.atlacademy.demo.dto.OrderDto;
import az.atlacademy.demo.request.OrderRequest;

import java.util.List;

public interface OrderService {

    OrderDto getById(Long orderId);

    OrderDto makeOrder(OrderRequest orderRequest);

    List<OrderDto>getAll();



}

