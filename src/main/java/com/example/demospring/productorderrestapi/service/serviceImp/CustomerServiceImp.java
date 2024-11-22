package com.example.demospring.productorderrestapi.service.serviceImp;

import com.example.demospring.productorderrestapi.exception.NotFoundException;
import com.example.demospring.productorderrestapi.model.Customer;
import com.example.demospring.productorderrestapi.model.request.CustomerRequest;
import com.example.demospring.productorderrestapi.repository.CustomerRepository;
import com.example.demospring.productorderrestapi.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.getAllCustomer();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.getCustomerById(id);
        if(customer == null){
            throw new NotFoundException("Customer by id "+ id + " not found!");
        }
        return customer;
    }

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepository.createCustomer(customerRequest);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerRequest customerRequest) {
        getCustomerById(id);
        return customerRepository.updateCustomer(id,customerRequest);
    }

    @Override
    public void deleteCustomer(Long id) {
        getCustomerById(id);
        customerRepository.deleteCustomer(id);
    }
}
