package az.atlacademy.demo.controller;

import az.atlacademy.demo.request.OrderRequest;
import az.atlacademy.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${project.url}")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.makeOrder(orderRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }


}
