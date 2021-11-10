package com.bouta.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Invoice {
    @Id
    private String id;
    private Date date;
    //montant du facture
    private BigDecimal amount;
    private String customerID;
    //ce attribut n'est pas persistent , JPA devrai l'ignorer puisque l'entity jpa customer se trouve dans un autre service
    @Transient
    private Customer customer;
}
