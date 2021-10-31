package com.asmaa.msbanque.repositories;

import com.asmaa.msbanque.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Long> {

}
