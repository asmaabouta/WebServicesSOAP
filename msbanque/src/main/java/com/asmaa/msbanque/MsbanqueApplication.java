package com.asmaa.msbanque;

import com.asmaa.msbanque.entities.Compte;
import com.asmaa.msbanque.entities.TypeCompte;
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
    CommandLineRunner start(CompteRepository compteRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            //pour afficher l id aussi
            restConfiguration.exposeIdsFor(Compte.class);
            compteRepository.save(new Compte(null,Math.random()*9000,new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null,Math.random()*9000,new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null,Math.random()*9000,new Date(), TypeCompte.EPARGNE));
            compteRepository.findAll().forEach(c->{
                System.out.println(c.toString());

            });
        };
    }

}
