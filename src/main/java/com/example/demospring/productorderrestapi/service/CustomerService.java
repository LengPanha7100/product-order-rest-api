package com.example.demospring.productorderrestapi.service;

import com.example.demospring.productorderrestapi.model.Customer;
import com.example.demospring.productorderrestapi.model.request.CustomerRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();

    Customer getCustomerById(Long id);

    Customer createCustomer(CustomerRequest customerRequest);

    Customer updateCustomer(Long id, CustomerRequest customerRequest);

    void deleteCustomer(Long id);
}
