package com.bouta.billingservice.openfeign;

import com.bouta.billingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//ceci est le nom qu'on a indiquer au service des customers
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    //cette methode va appeler le service des customer et récupére un client précis
    @GetMapping(path = "/api/customers/{id}")
    Customer getCustomer(@PathVariable(name = "id") String id);
    @GetMapping(path = "/api/customers")
    List<Customer> getAllCustomers();
}
