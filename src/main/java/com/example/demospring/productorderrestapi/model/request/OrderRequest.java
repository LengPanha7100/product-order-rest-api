package com.example.demospring.productorderrestapi.model.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer quantity;
    private Long productId;
}
