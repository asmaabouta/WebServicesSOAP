package com.asmaa.msbanque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    //par default nous avons le mode LAZY , c a d :par esseuille ; quand on demande de charger les clients il va pas charger les comptes
    @OneToMany(mappedBy ="client")
    //si SOAPUI ne marche pas correct pr xml ajouter cette annot avec @XmlRootElement et @XmlAccessorType(XmlAccessType.FIELD)
    @XmlTransient @JsonIgnore
    private Collection<Compte> comptes;

}
