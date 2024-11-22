package com.example.demospring.productorderrestapi.service;

import com.example.demospring.productorderrestapi.model.Product;
import com.example.demospring.productorderrestapi.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product getProductById(Long id);

    Product createProduct(ProductRequest productRequest);

    Product updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);
}
