package com.example.demospring.productorderrestapi.repository;

import com.example.demospring.productorderrestapi.model.Order;
import com.example.demospring.productorderrestapi.model.Product;
import com.example.demospring.productorderrestapi.model.enums.OrderEnums;
import com.example.demospring.productorderrestapi.model.request.OrderRequest;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderRepository {
    @Results(id = "orderId" , value = {
         @Result(property = "orderId" , column = "order_id")  ,
         @Result(property = "orderDate" , column = "order_date"),
         @Result(property = "totalAmount" , column = "total_amount"),
         @Result(property = "customer" , column = "customer_id",
         one = @One(select = "com.example.demospring.productorderrestapi.repository.CustomerRepository.getCustomerById")),
        @Result(property = "productList" , column = "order_id",
        many = @Many(select = "getAllProductByOrderId" )
        )
    })
    @Select("""
    SELECT * FROM order_tb;
    """)
    List<Order> getAllOrder();

    @Results(id = "productId" , value = {
            @Result(property = "productId" , column = "product_id"),
            @Result(property = "productName" , column = "product_name"),
            @Result(property = "unitPrice" , column = "unit_price")
    })
    @Select("""
    SELECT p.product_id,p.product_name,p.unit_price,p.description FROM product p
    JOIN product_order po on p.product_id = po.product_id WHERE po.order_id = #{orderId};
    """)
    List<Product> getAllProductByOrderId(Long orderId);

    @Select("""
    INSERT INTO order_tb(order_date, total_amount, status, customer_id)
    VALUES (#{orderDate},#{totalAmount},#{status},#{customerId}) 
    returning *;
    """)
    @ResultMap("orderId")
    Order createOrder(LocalDateTime orderDate, Float totalAmount, String status, Long customerId);

    @Insert("""
    INSERT INTO product_order(product_id, order_id, quantity)
    VALUES (#{order.productId},#{orderId},#{order.quantity});
    """)
    @ResultMap("orderId")
    void insertProductIdAndOrderId(Long orderId,@Param("order") OrderRequest orderRequest);

    @Select("""
    SELECT * FROM order_tb WHERE order_id = #{id};
    """)
    @ResultMap("orderId")
    Order getOrderById(Long id);

    @Update("""
    UPDATE order_tb
    SET total_amount = #{totalAmount}
    WHERE order_id = #{orderId}
    """)
    void updateProductByTotalAmount(Long orderId , Float totalAmount);

    @Select("""
    UPDATE order_tb
    SET status = #{orderEnums}
    WHERE order_id = #{orderId} AND customer_id = #{cusId}
    returning * ;
    """)
    @ResultMap("orderId")
    Order updateOrder(Long cusId, Long orderId, String orderEnums);
}
