package com.bouta.billingservice.services;

import com.bouta.billingservice.CustomerNotFoundException;
import com.bouta.billingservice.dto.InvoiceRespenseDTO;
import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.entities.Customer;
import com.bouta.billingservice.entities.Invoice;
import com.bouta.billingservice.mappers.InvoiceMapper;
import com.bouta.billingservice.entities.Product;
import com.bouta.billingservice.openfeign.CustomerRestClient;
import com.bouta.billingservice.openfeign.ProductItemRestClient;
import com.bouta.billingservice.repositories.InvoiceRepository;
import com.bouta.billingservice.repositories.ProductItemRestResource;
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

    private ProductItemRestResource productItemRestResource;
    private ProductItemRestClient productItemRestClient;

    // au moment de generation de ce constructeur , ca va poser un blem , la solution est d'ajouter @EnableFeignClients dans le prog princip
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;

    }

    @Override
    public InvoiceRespenseDTO save(InvoicerRequestDTO request) {
        // Now we're going to verify the existence of the csutomer (verification de l'integrité référentielle Invoice/Customer)
        // test de l 'integrité référentiel , si le customer existe
        Customer customer= null;
        try {
           customer=customerRestClient.getCustomer(request.getCustomerID());
        }catch (Exception e){
           throw new CustomerNotFoundException("Customer n'existe pas ");
        }
       Invoice invoice = invoiceMapper.invoiceDtoToInvoice(request);
       invoice.setId(UUID.randomUUID().toString());
       invoice.setDate(new Date());

       Invoice savedInvoice=invoiceRepository.save(invoice);
       InvoiceRespenseDTO respense=invoiceMapper.invoiceToInvoiceDto(savedInvoice);
      savedInvoice.setCustomer(customer);
       return  respense;
    }

    @Override
    public List<InvoiceRespenseDTO> getAllInvoices() {
       List<Invoice> invoices=invoiceRepository.findAll();
       for (Invoice invoice:invoices){
           Customer customer=customerRestClient.getCustomer(invoice.getCustomerID());
            invoice.setCustomer(customer);

           invoice.getProductItems().forEach(pi->{
               Product product=productItemRestClient.getProductById(pi.getProductId());
               pi.setName(product.getName());
               //System.out.println(pi.getProductId());
           });


       }


       return invoices.stream().map(inv ->invoiceMapper.invoiceToInvoiceDto(inv)).
               collect(Collectors.toList());
    }




    @Override
    public InvoiceRespenseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerID());
        invoice.setCustomer(customer);

        invoice.getProductItems().forEach(pi->{
            Product product=productItemRestClient.getProductById(pi.getProductId());
            pi.setName(product.getName());
            System.out.println(pi);
        });

        System.out.println(invoice);


        InvoiceRespenseDTO respenseDTO =invoiceMapper.invoiceToInvoiceDto(invoice);
        return  respenseDTO;
    }
    @Override
    public List<InvoiceRespenseDTO> InvoiceByCustomerId(String customerId) {
    List<Invoice> invoices=invoiceRepository.findByCustomerID(customerId);
        for (Invoice invoice:invoices){
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerID());
            invoice.setCustomer(customer);
            invoice.getProductItems().forEach(pi->{
                Product product=productItemRestClient.getProductById(pi.getProductId());
                pi.setName(product.getName());
            });

        }
        return invoices
            .stream()
            .map(invoice -> invoiceMapper.invoiceToInvoiceDto(invoice))
            .collect(Collectors.toList());
}





}
