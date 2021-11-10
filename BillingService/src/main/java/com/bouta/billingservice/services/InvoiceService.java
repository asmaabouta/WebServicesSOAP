package com.bouta.billingservice.services;

import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.dto.InvoiceRespenseDTO;

import java.util.List;

public interface InvoiceService {
     InvoiceRespenseDTO save(InvoicerRequestDTO invoicerRequestDTO);
     InvoiceRespenseDTO getInvoice(String invoiceId);
     List<InvoiceRespenseDTO> InvoiceByCustomerId(String customerId);
}
