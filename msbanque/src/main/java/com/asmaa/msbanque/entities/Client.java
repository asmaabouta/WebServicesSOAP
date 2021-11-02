package com.asmaa.msbanque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    @OneToMany(mappedBy ="client")
    private Collection<Compte> comptes;

}
