package com.bouta.billingservice.services;

import com.bouta.billingservice.CustomerNotFoundException;
import com.bouta.billingservice.dto.InvoiceRespenseDTO;
import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.entities.Customer;
import com.bouta.billingservice.entities.Invoice;
import com.bouta.billingservice.mappers.InvoiceMapper;
import com.bouta.billingservice.openfeign.CustomerRestClient;
import com.bouta.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        // test de l 'integrité référentiel , si le customer existe
        try {
            Customer customer=customerRestClient.getCustomer(request.getCustomerID());
        }catch (Exception e){
           throw new CustomerNotFoundException("Customer n'existe pas ");
        }
       Invoice invoice = invoiceMapper.invoiceDtoToInvoice(request);
       invoice.setId(UUID.randomUUID().toString());
       invoice.setDate(new Date());
       // Now we're going to verify the existence of the csutomer (verification de l'integrité référentielle Invoice/Customer)

       Invoice savedInvoice=invoiceRepository.save(invoice);
       InvoiceRespenseDTO respense=invoiceMapper.invoiceToInvoiceDto(savedInvoice);
       return  respense;
    }

    @Override
    public List<InvoiceRespenseDTO> getAllInvoices() {
       List<Invoice> invoices=invoiceRepository.findAll();
       for (Invoice invoice:invoices){
           Customer customer=customerRestClient.getCustomer(invoice.getCustomerID());
            invoice.setCustomer(customer);
       }
       return invoices.stream().map(inv ->invoiceMapper.invoiceToInvoiceDto(inv)).
               collect(Collectors.toList());
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
        for (Invoice invoice:invoices){
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerID());
            invoice.setCustomer(customer);
        }
        return invoices
            .stream()
            .map(invoice -> invoiceMapper.invoiceToInvoiceDto(invoice))
            .collect(Collectors.toList());
}


}
