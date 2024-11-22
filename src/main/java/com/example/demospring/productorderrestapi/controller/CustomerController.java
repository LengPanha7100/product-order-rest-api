package com.example.demospring.productorderrestapi.controller;

import com.example.demospring.productorderrestapi.model.Customer;
import com.example.demospring.productorderrestapi.model.request.CustomerRequest;
import com.example.demospring.productorderrestapi.model.response.APIResponse;
import com.example.demospring.productorderrestapi.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService ;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Customer>>> getAllCustomer(){
        List<Customer> customers = customerService.getAllCustomer();
        APIResponse<List<Customer>> apiResponse = APIResponse.<List<Customer>>builder()
                .message("Get all customer successfully!")
                .status(HttpStatus.OK)
                .payload(customers)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> getCustomerById(@PathVariable Long id){
        Customer customers = customerService.getCustomerById(id);
        APIResponse<Customer> apiResponse = APIResponse.<Customer>builder()
                .message("Get customer by id successfully!")
                .status(HttpStatus.OK)
                .payload(customers)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Customer>> createCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customers = customerService.createCustomer(customerRequest);
        APIResponse<Customer> apiResponse = APIResponse.<Customer>builder()
                .message("Created customer successfully!")
                .status(HttpStatus.CREATED)
                .payload(customers)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> updateCustomer(@PathVariable Long id,@RequestBody CustomerRequest customerRequest){
        Customer customers = customerService.updateCustomer(id,customerRequest);
        APIResponse<Customer> apiResponse = APIResponse.<Customer>builder()
                .message("Deleted customer by id successfully!")
                .status(HttpStatus.OK)
                .payload(customers)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Customer>> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        APIResponse<Customer> apiResponse = APIResponse.<Customer>builder()
                .message("Updated customer by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}

