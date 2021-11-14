package com.bouta.billingservice.dto;

import com.bouta.billingservice.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class InvoiceRespenseDTO {
    private String id;
    private Date date;
    private BigDecimal amount;
    private Customer customer;
}
