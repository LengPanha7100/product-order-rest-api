package com.example.demospring.productorderrestapi.service;

import com.example.demospring.productorderrestapi.model.Order;
import com.example.demospring.productorderrestapi.model.enums.OrderEnums;
import com.example.demospring.productorderrestapi.model.request.OrderRequest;

import java.util.List;

public interface OrderService {

    Order createOrder(List<OrderRequest> orderRequests, Long customerId);

    List<Order> getAllOrder();

    Order getOrderById(Long id);

    Order updateOrder(Long cusId, Long orderId, OrderEnums orderEnums);
}
