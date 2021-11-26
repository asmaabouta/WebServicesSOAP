package com.asmaa.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    //cette config va dire a spring cloud gateway que a chaque fois que y a une requete http qui arrive
    //tu regarde le path , le 1er elemebnt du path est le nom du microService
    //il envoie une rqt automatiquement vers discovery pr récuperer l 'adresse ip et le port
    // et il retourne le résultat
    @Bean
    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
            ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp)
    {
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }



}
