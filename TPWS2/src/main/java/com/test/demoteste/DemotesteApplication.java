package com.test.demoteste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
public class DemotesteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemotesteApplication.class, args);
    }
    // la methode suivante a comme oblectif , l'insertion des donnes dans la db
    //pour que cette methode s'execute au demarrage , on va utiliser l'annotation @Bean

    // HATEOS : Format Json qui est utiliser pr echanger les donnees entre applications
    // il est autoDescriptif : c a d ya les champs et ya aussi des links entre les ressources

    // le role de RepositoryRestConfig est d'utiliser la methode exposeId qui a comme but d'ajouter le champs id a l'affichage client
    @Bean
    CommandLineRunner start(ProduitRepository produitRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Produit.class);
            produitRepository.save(new Produit(null,"PC HP",5000.20,15));
            produitRepository.save(new Produit(null,"Imprimante",8000,8));
            produitRepository.save(new Produit(null,"Smart Phone",3000,10));
            produitRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });

        };
    }

}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;

}
@RepositoryRestResource
interface ProduitRepository extends JpaRepository<Produit,Long> {
// cette methode par default fonctionne avec localhost:8082/produits/search/findByNameContains?name=HP
   @RestResource(path = "/byName")
    Page<Produit> findByNameContains(String name, Pageable pageable);
}

//On utilise les projection afin d'afficher juste les champs qu'on veut C un peux comme GraphQl
@Projection(name = "mobile",types = Produit.class)
interface produitProjection{
    String getName();
}


@Projection(name = "web",types = Produit.class)
interface produitProjection2{
    String getName();
    String getPrice();
}
























/*@RestController
class ProduitRestController{
    @Autowired
    private ProduitRepository produitRepository;
    //Afficher la liste des produits
    @GetMapping(path = "/produits")
    public List<Produit> produitList(){
        return produitRepository.findAll();
    }

    //Afficher un produit spécifique
    @GetMapping(path = "/produits/{id}")
    public Produit getOneProdut(@PathVariable Long id){
        return produitRepository.findById(id).get();
    }

    //Inserer un produit
    @PostMapping(path = "/produits")
    public void saveProduct(@RequestBody Produit produit){
        produitRepository.save(produit);
    }

    //MAJ
    @PutMapping(path = "/produits/{id}")
    public Produit updateProduct(@PathVariable Long id,@RequestBody Produit produit){
      // si le produit existe deja il fait update
       produit.setId(id);
      // sinon il fait insert
        return produitRepository.save(produit);
    }

    //Supression
    @DeleteMapping(path = "/produits/{id}")
    public void deleteProduct(@PathVariable  Long id){
        Produit p=produitRepository.findById(id).get();
        produitRepository.delete(p);
    }
}
*/
// cette annotation est l'équivanlente de qui a été commenté @RepositoryRestResource



