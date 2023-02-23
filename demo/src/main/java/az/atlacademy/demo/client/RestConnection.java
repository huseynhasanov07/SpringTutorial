package az.atlacademy.demo.client;

import az.atlacademy.demo.domain.CustomerResponse;
import az.atlacademy.demo.domain.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@RequiredArgsConstructor
@Component
public class RestConnection {
    private final RestTemplate restTemplate;

    public void decreaseCount(Long productId, Long count) {
        restTemplate.patchForObject("http://localhost:8082/api/v1/products/" + productId + "/decrease-count?count=" + count, null,
                Serializable.class);
    }

    public void decreaseBalance(Long customerId, Long price) {
        restTemplate.patchForObject("http://localhost:8081/api/v1/customer/" + customerId + "/" + price, null, Serializable.class);
    }

    public ResponseEntity<CustomerResponse> getCustomer(Long id) {
        return restTemplate.getForEntity("http://localhost:8081/api/v1/customer/" + id, CustomerResponse.class, Serializable.class);
    }

    public ResponseEntity<ProductResponse> getProduct(Long id) {
        return restTemplate.getForEntity("http://localhost:8082/api/v1/products/" + id, ProductResponse.class, Serializable.class);
    }


}
