package com.example.demospring.productorderrestapi.controller;

import com.example.demospring.productorderrestapi.model.Product;
import com.example.demospring.productorderrestapi.model.request.ProductRequest;
import com.example.demospring.productorderrestapi.model.response.APIResponse;
import com.example.demospring.productorderrestapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAllProduct(){
        List<Product> products = productService.getAllProduct();
        APIResponse<List<Product>> apiResponse = APIResponse.<List<Product>>builder()
                .message("Get all product successfully!")
                .status(HttpStatus.OK)
                .payload(products)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable Long id){
        Product products = productService.getProductById(id);
        APIResponse<Product> apiResponse = APIResponse.<Product>builder()
                .message("Get product by id successfully!")
                .status(HttpStatus.OK)
                .payload(products)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Product>> createProduct(@RequestBody ProductRequest productRequest){
        Product products = productService.createProduct(productRequest);
        APIResponse<Product> apiResponse = APIResponse.<Product>builder()
                .message("Created product successfully!")
                .status(HttpStatus.CREATED)
                .payload(products)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        Product products = productService.updateProduct(id , productRequest);
        APIResponse<Product> apiResponse = APIResponse.<Product>builder()
                .message("Updated product by id successfully!")
                .status(HttpStatus.OK)
                .payload(products)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        APIResponse<Product> apiResponse = APIResponse.<Product>builder()
                .message("Deleted product by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }




}
