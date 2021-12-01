package com.bouta.billingservice;

import com.bouta.billingservice.dto.InvoiceRespenseDTO;
import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.entities.Invoice;
import com.bouta.billingservice.entities.Product;
import com.bouta.billingservice.entities.ProductItem;
import com.bouta.billingservice.mappers.InvoiceMapper;
import com.bouta.billingservice.openfeign.CustomerRestClient;
import com.bouta.billingservice.openfeign.ProductItemRestClient;
import com.bouta.billingservice.repositories.InvoiceRepository;
import com.bouta.billingservice.repositories.ProductItemRestResource;
import com.bouta.billingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.math.BigDecimal;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService, ProductItemRestResource productItemRestResource,
                            CustomerRestClient customerRestClient,
                            InvoiceRepository invoiceRepository,
                            ProductItemRestClient productItemRestClient, InvoiceMapper invoiceMapper){
        return args -> {
           InvoiceRespenseDTO inv =  invoiceService.save(new InvoicerRequestDTO(BigDecimal.valueOf(1000),"C02"));
            invoiceService.save(new InvoicerRequestDTO(BigDecimal.valueOf(8900),"C02"));
            invoiceService.save(new InvoicerRequestDTO(BigDecimal.valueOf(9000),"C01"));
            invoiceService.save(new InvoicerRequestDTO(BigDecimal.valueOf(9600),"C03"));

            invoiceService.getAllInvoices()
                    .forEach(invoice->{
                       //  log.info("Invoice: {}", invoice.getCustomer());
                       //  log.info("Invoice: {}", invoice.getAmount());
                        System.out.println(invoice.toString());
                    });

            //PagedModel<Product> productPagedModel=productItemRestClient.pageProducts(0,20);
            Product p=productItemRestClient.getProductById("SASAS");
            ProductItem productItem=new ProductItem();
            productItem.setId(p.getId());
            productItem.setPrice(p.getPrice());
            productItem.setQuantity(new Random().nextInt(100));
            productItem.setProductId(p.getId());
            productItem.setInvoice(invoiceRepository.findById(inv.getId()).get());
            productItemRestResource.save(productItem);
           /* productPagedModel.forEach(p->{
                ProductItem productItem=new ProductItem();
                productItem.setId(p.getId());
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(new Random().nextInt(100));
                productItem.setProductId(p.getId());
                productItem.setInvoice(invoiceRepository.findById(inv.getId()).get());
                productItemRestResource.save(productItem);
            });*/
        };
    }


    @Bean
    CommandLineRunner start(
            ProductItemRestClient productItemRestClient
    ){
        return args ->{

        };
    }

}
