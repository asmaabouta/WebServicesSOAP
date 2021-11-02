package com.asmaa.msbanque.web;

import com.asmaa.msbanque.entities.Compte;
import com.asmaa.msbanque.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

//On utilise @Component pr qu'on puisse faire l'Autowired
@Component
@WebService(serviceName = "BanqueWS")
public class CompteSOAPService {
    @Autowired
    private CompteRepository compteRepository;

    //Consulter tous les comptes
    @WebMethod
    public List<Compte> compteList(){
       return compteRepository.findAll();
    }

    //Consulter un compte
    @WebMethod
    public Compte getOne(@WebParam(name = "id") Long id){
      return compteRepository.findById(id).get();
    }



}
