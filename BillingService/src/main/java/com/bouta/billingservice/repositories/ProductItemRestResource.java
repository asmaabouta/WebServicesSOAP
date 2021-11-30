package com.bouta.billingservice.repositories;

import com.bouta.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface ProductItemRestResource extends JpaRepository<ProductItem,String> {
    List<ProductItem> findByInvoiceId(Long invoiceID);
}
