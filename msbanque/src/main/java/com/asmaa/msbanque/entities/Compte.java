package com.asmaa.msbanque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  double solde;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    //cette annotation JPA nous permet de choisir juste de 2 options du compte
    //ordinal signifie qu'au sein de la bd le champ type soit numerique (0 ou 1),0: Courant , 1 :epargne
    //sinon si on veut stocker ce champ en tant que String , on va mettre EnumType.STRING
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TypeCompte type;
    //et pour avoir un MS complet , il nous reste d'ajouter les connecteurs SOAP ou REST
    //pour les connecteurs REST , on a 3 techniques:
      //JaxRs
      //RestController
      //Spring Data Rest
}
