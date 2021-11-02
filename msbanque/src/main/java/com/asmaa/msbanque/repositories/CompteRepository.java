package com.asmaa.msbanque.repositories;

import com.asmaa.msbanque.entities.Compte;
import com.asmaa.msbanque.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte,Long> {
//accessible via /api/comptes/search/findByType?type=COURANT
//public List<Compte> findByType(TypeCompte type);
// on peux chercher de cette maniere aussi :
//comme ca il suffit de tapper api/comptes/search/byType?kw=COURANT

@RestResource(path = "/byType")
public List<Compte> findByType(@Param("kw")TypeCompte type);
}
