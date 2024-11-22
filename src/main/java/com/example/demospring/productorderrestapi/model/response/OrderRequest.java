package com.example.demospring.productorderrestapi.model.response;

import com.example.demospring.productorderrestapi.model.enums.OrderEnums;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long orderId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateTime;
    private Float totalAmount;
    private OrderEnums status;

}
