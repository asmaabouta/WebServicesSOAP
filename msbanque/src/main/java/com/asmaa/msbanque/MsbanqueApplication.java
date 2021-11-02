package com.asmaa.msbanque;

import com.asmaa.msbanque.entities.Client;
import com.asmaa.msbanque.entities.Compte;
import com.asmaa.msbanque.entities.TypeCompte;
import com.asmaa.msbanque.repositories.ClientRepository;
import com.asmaa.msbanque.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class MsbanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsbanqueApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CompteRepository compteRepository, RepositoryRestConfiguration restConfiguration, ClientRepository clientRepository){
        return args -> {
            Client c1= clientRepository.save(new Client(null,"Asmaa",null));
            Client c2= clientRepository.save(new Client(null,"Safaa",null));

            //pour afficher l id aussi
            restConfiguration.exposeIdsFor(Compte.class);
            compteRepository.save(new Compte(null,Math.random()*9000,new Date(), TypeCompte.EPARGNE,c1));
            compteRepository.save(new Compte(null,Math.random()*9000,new Date(), TypeCompte.COURANT,c1));
            compteRepository.save(new Compte(null,Math.random()*9000,new Date(), TypeCompte.EPARGNE,c2));
            compteRepository.findAll().forEach(c->{
                System.out.println(c.getSolde());
                System.out.println(c.getDateCreation());
                System.out.println(c.getType());
                System.out.println(c.getClient().getName());

            });
        };
    }

}
