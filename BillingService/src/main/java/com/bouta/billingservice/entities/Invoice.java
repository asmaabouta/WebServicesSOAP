package com.bouta.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Invoice {
    @Id
    private String id;
    private Date date;
    //montant du facture
    private BigDecimal amount;
    //ce attribut n'est pas persistent , JPA devrai l'ignorer puisque l'entity jpa customer se trouve dans un autre service
    @Transient @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    private String customerID;
    @Transient
    private Customer customer;
}
