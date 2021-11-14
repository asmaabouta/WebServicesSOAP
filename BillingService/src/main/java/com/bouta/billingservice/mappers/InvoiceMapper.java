package com.bouta.billingservice.mappers;

import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.dto.InvoiceRespenseDTO;
import com.bouta.billingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
 Invoice invoiceDtoToInvoice(InvoicerRequestDTO invoicerRequestDTO);
 InvoiceRespenseDTO invoiceToInvoiceDto(Invoice invoice);

}
