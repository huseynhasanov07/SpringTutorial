package az.atlacademy.demo.checker;

import az.atlacademy.demo.domain.CustomerResponse;
import az.atlacademy.demo.util.BalanceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerChecker {
    public void checker(CustomerResponse customerExample, BigDecimal orderPrice) {
        if (BigDecimal.valueOf(customerExample.getBalance()).compareTo(orderPrice) < 0) {
            throw new BalanceException("Not enough money");
        }
    }
}
