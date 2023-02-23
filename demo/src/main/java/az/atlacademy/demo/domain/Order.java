package az.atlacademy.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private Long productId;
    private int count;
    private Long price;
    private Date orderedAt;

    @PrePersist
    void init() {
        setOrderedAt(new Date());
    }


}
