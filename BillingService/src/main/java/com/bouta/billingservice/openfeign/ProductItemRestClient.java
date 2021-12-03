package com.bouta.billingservice.openfeign;

import com.bouta.billingservice.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductItemRestClient {
    @GetMapping(path = "/api/products")
    PagedModel<Product> pageProducts(@RequestParam(value = "page") int page,
                                     @RequestParam(value = "size") int size);
    @GetMapping(path = "/api/products/{id}")
    Product getProductById(@PathVariable String id);
}
