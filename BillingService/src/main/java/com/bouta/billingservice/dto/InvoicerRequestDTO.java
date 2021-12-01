package com.bouta.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data @AllArgsConstructor @NoArgsConstructor
public class InvoicerRequestDTO {

    private BigDecimal amount;
    private String customerID;

}
