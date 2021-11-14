package com.bouta.billingservice.services;

import com.bouta.billingservice.dto.InvoiceRespenseDTO;
import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.entities.Customer;
import com.bouta.billingservice.entities.Invoice;
import com.bouta.billingservice.mappers.InvoiceMapper;
import com.bouta.billingservice.openfeign.CustomerRestClient;
import com.bouta.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;
                                                                                                                                                // au moment de generation de ce constructeur , ca va poser un blem , la solution est d'ajouter @EnableFeignClients dans le prog princip
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceRespenseDTO save(InvoicerRequestDTO request) {
       Invoice invoice = invoiceMapper.invoiceDtoToInvoice(request);
       invoice.setId(UUID.randomUUID().toString());
       Invoice savedInvoice=invoiceRepository.save(invoice);
       InvoiceRespenseDTO respense=invoiceMapper.invoiceToInvoiceDto(savedInvoice);
       return  respense;
    }

    @Override
    public InvoiceRespenseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerID());
        invoice.setCustomer(customer);
        InvoiceRespenseDTO respenseDTO =invoiceMapper.invoiceToInvoiceDto(invoice);
        return  respenseDTO;
    }
    @Override
    public List<InvoiceRespenseDTO> InvoiceByCustomerId(String customerId) {
    List<Invoice> invoices=invoiceRepository.findByCustomerID(customerId);
    return invoices
            .stream()
            .map(invoice -> invoiceMapper.invoiceToInvoiceDto(invoice))
            .collect(Collectors.toList());
}
}
