package com.example.demospring.productorderrestapi.repository;

import com.example.demospring.productorderrestapi.model.Product;
import com.example.demospring.productorderrestapi.model.request.ProductRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductRepository {
    @Results(id = "productId" , value = {
            @Result(property = "productId" , column = "product_id"),
            @Result(property = "productName" , column = "product_name"),
            @Result(property = "unitPrice" , column = "unit_price")
    })
    @Select("""
    SELECT *FROM product;
    """)
    List<Product> getAllProduct();

    @Select("""
    SELECT *FROM product WHERE product_id = #{id};
    """)
    @ResultMap("productId")
    Product getProductById(Long id);

    @Select("""
    INSERT INTO product(product_name, unit_price, description)
    VALUES (#{product.productName}, #{product.unitPrice},#{product.description}) returning *;
    """)
    @ResultMap("productId")
    Product createProduct(@Param("product") ProductRequest productRequest);

    @Select("""
    UPDATE product
    SET product_name = #{product.productName} , unit_price = #{product.unitPrice} , description = #{product.description}
    WHERE product_id = #{id}
    returning * ;
    """)
    @ResultMap("productId")
    Product updateProduct(Long id, @Param("product") ProductRequest productRequest);

    @Delete("""
    DELETE FROM product WHERE  product_id = #{id} ;
    """)
    @ResultMap("productId")
    void deleteProduct(Long id);
}
