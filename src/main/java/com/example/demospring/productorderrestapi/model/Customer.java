package com.example.demospring.productorderrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Email email;
}
