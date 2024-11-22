package com.example.demospring.productorderrestapi.service.serviceImp;

import com.example.demospring.productorderrestapi.exception.NotFoundException;
import com.example.demospring.productorderrestapi.model.Customer;
import com.example.demospring.productorderrestapi.model.Order;
import com.example.demospring.productorderrestapi.model.enums.OrderEnums;
import com.example.demospring.productorderrestapi.model.request.OrderRequest;
import com.example.demospring.productorderrestapi.repository.OrderRepository;
import com.example.demospring.productorderrestapi.service.CustomerService;
import com.example.demospring.productorderrestapi.service.OrderService;
import com.example.demospring.productorderrestapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderServiceImp(OrderRepository orderRepository, ProductService productService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public Order createOrder(List<OrderRequest> orderRequests, Long customerId) {
        Order order = orderRepository.createOrder(LocalDateTime.now(),23.0f,"SHIPPED",customerId);
        Float totalAmount = 0.f;
        for (OrderRequest orderRequest : orderRequests) {
            orderRepository.insertProductIdAndOrderId(order.getOrderId(), orderRequest);
            totalAmount += orderRequest.getQuantity() * productService.getProductById(orderRequest.getProductId()).getUnitPrice();
        }
        orderRepository.updateProductByTotalAmount(order.getOrderId() , totalAmount);
        return getOrderById(order.getOrderId());
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.getAllOrder();
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderRepository.getOrderById(id);
        if(order == null){
            throw new NotFoundException("Get order by id "+ id + " not found");
        }
        return order;
    }

    @Override
    public Order updateOrder(Long cusId, Long orderId, OrderEnums orderEnums) {
        customerService.getCustomerById(cusId);
        getOrderById(orderId);
        Order order = orderRepository.updateOrder(cusId,orderId,orderEnums.name());
        return order;
    }
}
