package com.food.ordering.system.restaurant.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;

import java.util.List;

public class OrderDetail extends BaseEntity<OrderId> {

    private final List<Product> products;
    private final OrderStatus orderStatus;
    private final Money totalAmount;

    private OrderDetail(Builder builder) {
        setId(builder.orderId);
        products = builder.products;
        orderStatus = builder.orderStatus;
        totalAmount = builder.totalAmount;
    }

    public static Builder builder() {
        return new Builder();
    }


    public List<Product> getProducts() {
        return products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }


    public static final class Builder {
        private OrderId orderId;
        private List<Product> products;
        private OrderStatus orderStatus;
        private Money totalAmount;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}
