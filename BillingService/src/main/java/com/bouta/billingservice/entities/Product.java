package com.bouta.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// cette classe n'est pas une classe JPA puisque il est pas persistante
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private Double price;
}
