import ws.BanqueService;
import ws.BanqueWS;
import ws.Compte;

import java.util.List;

// on a creer cette classe pour consommer le web service
public class ClientWS {
    public static void main(String[] args) {
        // le ws c nptre proxy ou bien notre stub
        BanqueService stubWS=new BanqueWS().getBanqueServicePort();
        // ici le client demande au proxy (l'intermediaire coté client) de faire appl a la methode convertEuroToDH pr que le proxy envoi la requete SOAP qui va etre recu par (l'intermediaire coté serveur) "skeleton(Qui est JaxWS)"
        System.out.println(stubWS.conversionEuroToDH(20));
        Compte compte=stubWS.getCompte(2L);
        System.out.println(compte.getCode());
        System.out.println(compte.getSolde());
        List<Compte> comptes=stubWS.listComptes();

        comptes.forEach(c->{
            System.out.println("------------");
            System.out.println(c.getCode());
            System.out.println(c.getSolde());
        });
    }
}
