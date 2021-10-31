package com.asmaa.msbanque.web;

import com.asmaa.msbanque.entities.Compte;
import com.asmaa.msbanque.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

//on a choisi JaxRS comme connecteurs Restfull , pr ça on doit ajouter @Pat
//Nous avons l'habitude de travailler avec RestController , c a d un composant spring , ms cette fois on travaille avec path , et pour indiquer que c un composant spring on ajoute @Component
@Component
@Path("/banque")
public class CompteRestJaxRSAPI {
    @Autowired
    private CompteRepository compteRepository;
    // Nous avons l'habitude de travailler avec GetMapping dans le cas de spring ms cette fois JaxRS propose ces fonctionnalitées comme suit
    @Path("/comptes")
    @GET
    //Indiquer le format de retour
    @Produces({MediaType.APPLICATION_JSON})
    public List<Compte> compteList(){
       return compteRepository.findAll();
    }
}
