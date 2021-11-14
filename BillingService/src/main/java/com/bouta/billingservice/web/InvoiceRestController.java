package com.bouta.billingservice.web;

import com.bouta.billingservice.dto.InvoiceRespenseDTO;
import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.services.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping(path = "/invoices/{id}")
    public InvoiceRespenseDTO getInvoice(@PathVariable(name = "id") String invoiceId){
      return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping(path = "/invoices")
    public List<InvoiceRespenseDTO> getAllinvoices(){
        return invoiceService.getAllInvoices();
    }
    @PostMapping
    public InvoiceRespenseDTO saveInvoice(@PathVariable InvoicerRequestDTO requestDTO){
        return invoiceService.save(requestDTO);
    }
    @GetMapping(path = "/invoices/{customerId}")
    public List<InvoiceRespenseDTO> getByCustomerId(@PathVariable String customerId){
        return invoiceService.InvoiceByCustomerId(customerId);
    }
}

