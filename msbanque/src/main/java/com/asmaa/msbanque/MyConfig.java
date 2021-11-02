package com.asmaa.msbanque;

import com.asmaa.msbanque.web.CompteRestJaxRSAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@Configuration
public class MyConfig {
    // pour Déploiyer jersey
    //@Bean
    public ResourceConfig resourceConfig(){
        ResourceConfig jerseyServlet=new ResourceConfig();
        jerseyServlet.register(CompteRestJaxRSAPI.class);
        return jerseyServlet;
    }

    // Integrer JAXWS pour Soap Service
    @Bean
    SimpleJaxWsServiceExporter serviceExporter(){
    SimpleJaxWsServiceExporter serviceExporter=new SimpleJaxWsServiceExporter();
    serviceExporter.setBaseAddress("http://0.0.0.0:8888/");
    return serviceExporter;
    }
    //pour ce fait il faut aller vers l'url: localhost:8888/BanqueWS?wsdl
    //et comme ca nous aurons notre wsdl avec lequel on peux consommer notre ws
}
