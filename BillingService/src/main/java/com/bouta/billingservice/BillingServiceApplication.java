package com.bouta.billingservice;

import com.bouta.billingservice.dto.InvoicerRequestDTO;
import com.bouta.billingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoicerRequestDTO(BigDecimal.valueOf(1000),"C02"));
            invoiceService.save(new InvoicerRequestDTO(BigDecimal.valueOf(8900),"C02"));
            invoiceService.save(new InvoicerRequestDTO(BigDecimal.valueOf(900),"C15"));

            invoiceService.getAllInvoices()
                    .forEach(invoice->{
                       //  log.info("Invoice: {}", invoice.getCustomer());
                       //  log.info("Invoice: {}", invoice.getAmount());
                        System.out.println(invoice.toString());
                    });
        };
    }

}
