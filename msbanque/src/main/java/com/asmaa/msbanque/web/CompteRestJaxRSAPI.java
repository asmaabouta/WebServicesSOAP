package com.asmaa.msbanque.web;

import com.asmaa.msbanque.entities.Compte;
import com.asmaa.msbanque.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.Max;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//on a choisi JaxRS comme connecteurs Restfull , pr ça on doit ajouter @Pat
//Nous avons l'habitude de travailler avec RestController , c a d un composant spring , ms cette fois on travaille avec path , et pour indiquer que c un composant spring on ajoute @Component
@Component
@Path("/banque")
public class CompteRestJaxRSAPI {
    @Autowired
    private CompteRepository compteRepository;

    // On va creer une RestAPI on utilisant JaxRS c a d derriere ya jersey : servlet pas dispatcherServlet

    // Nous avons l'habitude de travailler avec GetMapping dans le cas de spring ms cette fois JaxRS propose ces fonctionnalitées comme suit
    //Consulter tous les comptes
    @Path("/comptes")
    @GET
    //Indiquer le format de retour
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Compte> compteList(){
       return compteRepository.findAll();
    }

    //Consulter un compte
    @Path("/comptes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    //on fait @PathVariable pr Spring et PathParam pour JaxRS
    public Compte getCompte(@PathParam(value ="id") Long id){
      return compteRepository.findById(id).get();
    }

    //Ajouter un compte
    @Path("/comptes")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    //Dans JaxRS , we don't need to specify the requestBody
    public Compte save(Compte compte){
        return compteRepository.save(compte);
    }

    //MAJ un compte
    @Path("/comptes/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Compte update(Compte compte,@PathParam(value = "id") Long id){
        compte.setId(id);
        return compteRepository.save(compte);
    }

    //Supprimer un compte
    @Path("/comptes/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public void delete(@PathParam(value = "id") Long id){
         compteRepository.deleteById(id);
    }


}
