package az.atlacademy.demo.checker;

import az.atlacademy.demo.domain.ProductResponse;
import az.atlacademy.demo.request.OrderRequest;
import az.atlacademy.demo.util.StockException;

import org.springframework.stereotype.Component;

@Component
public class ProductChecker {
    public void check(ProductResponse productResponse, OrderRequest orderRequest) {
        if (productResponse.getCount() < orderRequest.getCount()) {
            throw new StockException("Not enough food in stock");
        }
    }

}
