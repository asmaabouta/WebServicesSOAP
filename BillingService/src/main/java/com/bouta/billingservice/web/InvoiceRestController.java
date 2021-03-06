package com.bouta.billingservice.web;

import com.bouta.billingservice.dto.InvoiceRespenseDTO;
import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices")
    public List<InvoiceRespenseDTO> getAllinvoices(){
        return invoiceService.getAllInvoices();
    }

    @PostMapping(path = "/invoices")
    public InvoiceRespenseDTO saveInvoice(@RequestBody InvoicerRequestDTO requestDTO){
        return invoiceService.save(requestDTO);
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceRespenseDTO getInvoice(@PathVariable(name = "id") String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping(path = "/invoicesByCustomerId/{customerId}")
    public List<InvoiceRespenseDTO> InvoiceByCustomerId(@PathVariable String customerId){
        return invoiceService.InvoiceByCustomerId(customerId);
    }
    // cette fct permet de faire appel
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

