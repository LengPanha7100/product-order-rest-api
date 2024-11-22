package com.example.demospring.productorderrestapi.repository;

import com.example.demospring.productorderrestapi.model.Customer;
import com.example.demospring.productorderrestapi.model.request.CustomerRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerRepository {

    @Results(id = "customerId" , value = {
            @Result(property = "customerId" , column =  "customer_id"),
            @Result(property = "customerName" , column = "customer_name"),
            @Result(property = "phoneNumber" , column = "phone_number"),
            @Result(property = "email" , column = "email",
            one = @One(select = "com.example.demospring.productorderrestapi.repository.EmailRepository.getEmailByName")
            )
    })
    @Select("""
    SELECT * FROM customer;
    """)
    List<Customer> getAllCustomer();

    @Select("""
    SELECT *FROM customer WHERE customer_id = #{id};
    """)
    @ResultMap("customerId")
    Customer getCustomerById(Long id);

    @Select("""
    INSERT INTO customer(customer_name, address, phone_number, email)
    VALUES (#{customer.customerName}, #{customer.address}, #{customer.phoneNumber}, #{customer.email}) returning *;
    """)
    @ResultMap("customerId")
    Customer createCustomer(@Param("customer") CustomerRequest customerRequest);

    @Select("""
    UPDATE customer
    SET customer_name = #{customer.customerName} , address = #{customer.address} , phone_number = #{customer.phoneNumber} , email = #{customer.email}
    WHERE customer_id = #{id}
    returning * ;
    """)
    @ResultMap("customerId")
    Customer updateCustomer(Long id, @Param("customer") CustomerRequest customerRequest);

    @Select("""
    DELETE FROM customer WHERE customer_id = #{id};
    """)
    @ResultMap("customerId")
    void deleteCustomer(Long id);
}
