package com.example.demospring.productorderrestapi.service.serviceImp;

import com.example.demospring.productorderrestapi.exception.NotFoundException;
import com.example.demospring.productorderrestapi.model.Product;
import com.example.demospring.productorderrestapi.model.request.ProductRequest;
import com.example.demospring.productorderrestapi.repository.ProductRepository;
import com.example.demospring.productorderrestapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.getProductById(id);
        if(product == null){
            throw new NotFoundException("Product by id "+ id + " not found!");
        }
        return product;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return productRepository.createProduct(productRequest);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest productRequest) {
        getProductById(id);
        return productRepository.updateProduct(id,productRequest);
    }

    @Override
    public void deleteProduct(Long id) {
        getProductById(id);
        productRepository.deleteProduct(id);
    }
}
