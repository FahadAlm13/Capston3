package spring.boot.capston2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.capston2.API.ApiException;
import spring.boot.capston2.Model.Orders;
import spring.boot.capston2.Service.OrdersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.status(200).body(ordersService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@Valid @RequestBody Orders orders) {
        ordersService.addOrder(orders);
        return ResponseEntity.status(200).body(new ApiException("Order successfully added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @Valid @RequestBody Orders orders) {
        ordersService.updateOrder(id, orders);
        return ResponseEntity.status(200).body(new ApiException("Order successfully updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.status(200).body(new ApiException("Order successfully deleted"));
    }

    @PutMapping("/assignUsersToOrders/{id}")
    public ResponseEntity assignUsersToOrders(@PathVariable Integer id, @RequestParam Integer userId) {
        ordersService.assignUsersToOrders(id, userId);
        return ResponseEntity.status(200).body("assign Users To Orders");
    }
}
