package com.example.demospring.productorderrestapi.model;

import com.example.demospring.productorderrestapi.model.enums.OrderEnums;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
    private Float totalAmount;
    private OrderEnums status;
    private List<Product> productList;
    private Customer customer;

}
