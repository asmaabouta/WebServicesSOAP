package com.asmaa.asmaaeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AsmaaEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsmaaEurekaServiceApplication.class, args);
    }

}
