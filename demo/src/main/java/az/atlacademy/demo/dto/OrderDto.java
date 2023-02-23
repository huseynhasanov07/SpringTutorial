package az.atlacademy.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto{
        private Long id;
        private Long customerId;
        private Long productId;
        private int count;
        private Long price;


}
