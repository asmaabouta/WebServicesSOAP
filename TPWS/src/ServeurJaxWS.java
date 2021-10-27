import ws.BanqueService;

import javax.xml.ws.Endpoint;
// ici on cree le serveur JaxWS qui deploi le ws
public class ServeurJaxWS {
    public static void main(String[] args) {
        //on met 0.0.0.0 pour qu'on accéde au ws de n 'importe quel réseau
        // c a d que ca soit l 'adresse IP
        String url="http://0.0.0.0:8686/";
        Endpoint.publish(url,new BanqueService());
        System.out.println("web service déployé sur "+url);
    }
    // dans le navigateur , on tappe :http://localhost:8686/BanqueWS?wsdl
    //un fichier xml sera affiché : c le document wsdl
    // ce qui nous intéresse c la partie portType car il contient l ensemble des opérations et ses input /output

}
