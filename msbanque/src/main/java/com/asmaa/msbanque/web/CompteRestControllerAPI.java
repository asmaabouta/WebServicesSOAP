package com.asmaa.msbanque.web;

import com.asmaa.msbanque.entities.Compte;
import com.asmaa.msbanque.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//ici on utilise dispatcherServlet comme servlet
//@RestController
@RequestMapping("/banque")
public class CompteRestControllerAPI {
    @Autowired
    private CompteRepository compteRepository;

    @GetMapping(value = "/comptes",produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    public List<Compte> compteList(){
       return compteRepository.findAll();
    }

    //Consulter un compte
    @GetMapping(value = "/comptes/{id}")
    //on fait @PathVariable pr Spring et PathParam pour JaxRS
    public Compte getCompte(@PathVariable Long id){
      return compteRepository.findById(id).get();
    }

    //Ajouter un compte
   @PostMapping(path = "/comptes")
    public Compte save(@RequestBody Compte compte){
        return compteRepository.save(compte);
    }

    //MAJ un compte
   @PutMapping(path = "/comptes/{id}")
    public Compte update(@RequestBody Compte compte,@PathVariable Long id){
        compte.setId(id);
        return compteRepository.save(compte);
    }

    //Supprimer un compte
    @DeleteMapping(path = "/comptes/{id}")
    public void delete(@PathVariable Long id) {
         compteRepository.deleteById(id);

    }


}

// la derniere methode qui reste pour les connecteurs Rest c'est (Spring Data Rest)
// pour ce fait , il suffit juste d'ajouter l'annotation @RepositoryRestResource a l'interface JPA et on execute
// quant a l url il suffit juste de tapper : localhost:8082/comptes
