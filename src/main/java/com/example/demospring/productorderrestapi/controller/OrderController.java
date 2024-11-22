package com.example.demospring.productorderrestapi.controller;
import com.example.demospring.productorderrestapi.model.Order;
import com.example.demospring.productorderrestapi.model.enums.OrderEnums;
import com.example.demospring.productorderrestapi.model.request.CustomerRequest;
import com.example.demospring.productorderrestapi.model.request.OrderRequest;
import com.example.demospring.productorderrestapi.model.response.APIResponse;
import com.example.demospring.productorderrestapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Order>>> getAllOrder(){
        List<Order> orders = orderService.getAllOrder();
        APIResponse<List<Order>> apiResponse = APIResponse.<List<Order>>builder()
                .message("Get all order successfully!")
                .status(HttpStatus.OK)
                .payload(orders)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Order>> getOrderById(@PathVariable Long id){
        Order orders = orderService.getOrderById(id);
        APIResponse<Order> apiResponse = APIResponse.<Order>builder()
                .message("Get all order successfully!")
                .status(HttpStatus.OK)
                .payload(orders)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<APIResponse<Order>> createOrder(@RequestBody List<OrderRequest> orderRequests , @PathVariable Long customerId){
        Order order = orderService.createOrder(orderRequests,customerId);
        APIResponse<Order> apiResponse = APIResponse.<Order>builder()
                .message("Created customer successfully!")
                .status(HttpStatus.CREATED)
                .payload(order)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{cusId}/order/{orderId}")
    public ResponseEntity<APIResponse<Order>> updateOrder(@PathVariable Long cusId , @PathVariable Long orderId , @RequestParam OrderEnums orderEnums){
        Order order = orderService.updateOrder(cusId,orderId,orderEnums);
        APIResponse<Order> apiResponse = APIResponse.<Order>builder()
                .message("Created customer successfully!")
                .status(HttpStatus.CREATED)
                .payload(order)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
