package com.bouta.billingservice.repositories;

import com.bouta.billingservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InvoiceRepository extends JpaRepository<Invoice,String> {
    List<Invoice> findByCustomerID(String customerId);
}
