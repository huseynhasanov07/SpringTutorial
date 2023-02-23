package az.atlacademy.demo.mapper;

import az.atlacademy.demo.domain.Order;
import az.atlacademy.demo.dto.OrderDto;
import az.atlacademy.demo.request.OrderRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderDto getRequestToDto(OrderRequest orderRequest);

    Order getDtoToEntity(OrderDto orderDto);

    OrderDto getEntityToDto(Order order);

    List<OrderDto>getEntityListToDtoList(List<Order>list);

    Order requestToEntity(OrderRequest orderRequest);

}
